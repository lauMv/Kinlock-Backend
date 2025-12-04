package com.app.kinlock.common.security;

import com.app.kinlock.domain.implement.CompositeUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

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
        String path = request.getServletPath();

        if (path.startsWith("/auth") ||
                MATCHER.match("/clientPlans/add", path) ||          // <- add this
                MATCHER.match("/insurances/list", path) ||
                MATCHER.match("/insurances/getById/**", path) ||
                MATCHER.match("/brokers/list", path) ||
                MATCHER.match("/plans/list", path) ||
                MATCHER.match("/plans/getById/**", path) ||
                MATCHER.match("/benefits/list", path) ||
                MATCHER.match("/regionals/list", path) ||
                MATCHER.match("/regionals/getById/**", path) ||
                MATCHER.match("/vehicleCatalog/list", path) ||
                MATCHER.match("/vehicleCatalog/getById/**", path) ||
                MATCHER.match("/vehicleCatalog/vehicleClassification", path) ||
                MATCHER.match("/benefits/benefitCoverageType", path) ||
                MATCHER.match("/planBenefits/list/byPlanId/**", path) ||
                MATCHER.match("/plans/sendEmail/**", path)) {
            chain.doFilter(request, response);
            return;
        }

        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String token = header.substring(7);
        if (!jwtUtil.validate(token)) {
            chain.doFilter(request, response);
            return;
        }

        String email = jwtUtil.extractUsername(token);
        try {
            UserDetails user = userDetailsService.loadUserByUsername(email);
            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(
                            user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (UsernameNotFoundException ex) {
            chain.doFilter(request, response);
            return;
        }
        chain.doFilter(request, response);
    }
}
