package com.app.kinlock.common.security;

import com.app.kinlock.data.BrokerRepository;
import com.app.kinlock.domain.entity.Broker;
import com.app.kinlock.domain.implement.CompositeUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CompositeUserDetailsService userDetailsService;
    private static final AntPathMatcher MATCHER = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        String path = request.getServletPath();          // ← more reliable
        if (path.startsWith("/auth")) {                  // simple prefix
            chain.doFilter(request, response);
            return;
        }
        /* 1. PUBLIC PATHS – skip token logic completely */
        if (MATCHER.match("/auth/**", path)) {
            chain.doFilter(request, response);
            return;
        }

        /* 2. existing JWT logic for everything else */
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);
        if (!jwtUtil.validate(token)) {
            chain.doFilter(request, response); return;
        }

        String email = jwtUtil.extractUsername(token);
        try {
            UserDetails user = userDetailsService.loadUserByUsername(email);
            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(
                            user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (UsernameNotFoundException ex) {
            log.warn("User not found: {}", email);
            chain.doFilter(request, response);
            return;
        }
        chain.doFilter(request, response);
    }
}
