package com.app.kinlock.domain.implement;

import com.app.kinlock.common.enums.RoleEnum;
import com.app.kinlock.common.security.JwtUtil;
import com.app.kinlock.domain.service.AuthService;
import com.app.kinlock.presentation.dto.LoginRequest;
import com.app.kinlock.presentation.pojo.LoginResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserDetailsService uds;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    @Override
    public LoginResponse authenticate(LoginRequest req) {
        UserDetails ud = uds.loadUserByUsername(req.getEmail());
        boolean ok = encoder.matches(req.getPassword(), ud.getPassword());
        if (!ok) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);

        String authority = ud.getAuthorities().iterator().next().getAuthority();
        String token = jwtUtil.generate(ud.getUsername(), authority);
        RoleEnum role = RoleEnum.valueOf(authority);
        return new LoginResponse(token, role);
    }
}