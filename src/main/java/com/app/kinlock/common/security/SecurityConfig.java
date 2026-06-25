package com.app.kinlock.common.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login", "/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/plans/list").permitAll()
                        .requestMatchers(HttpMethod.GET, "/plans/getById/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/benefits/list").permitAll()
                        .requestMatchers(HttpMethod.GET, "/benefits/benefitCoverageType").permitAll()
                        .requestMatchers(HttpMethod.GET, "/benefits/getById/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/insurances/list").permitAll()
                        .requestMatchers(HttpMethod.GET, "/insurances/getById/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/planBenefits/list/byPlanId/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/regionals/list").permitAll()
                        .requestMatchers(HttpMethod.GET, "/regionals/getById/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/vehicleCatalog/list").permitAll()
                        .requestMatchers(HttpMethod.GET, "/vehicleCatalog/vehicleClassification").permitAll()
                        .requestMatchers(HttpMethod.GET, "/vehicleCatalog/getById/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/vehicleCatalog/allBrands").permitAll()
                        .requestMatchers(HttpMethod.GET, "/vehicleCatalog/allModelsByBrand/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/brokers/list").permitAll()
                        .requestMatchers(HttpMethod.POST, "/clientPlans/add").permitAll()
                        .requestMatchers(HttpMethod.POST, "/plans/search").permitAll()
                        .requestMatchers(HttpMethod.POST, "/plans/sendEmail/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/broker/**").hasAnyRole("BROKER", "ADMIN")
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public org.springframework.web.cors.CorsConfigurationSource corsConfigurationSource() {
        org.springframework.web.cors.CorsConfiguration cfg =
                new org.springframework.web.cors.CorsConfiguration();
        cfg.setAllowedOriginPatterns(List.of("http://localhost:4200"));
        cfg.setAllowedMethods(List.of("*"));
        cfg.setAllowedHeaders(List.of("*"));
        cfg.setExposedHeaders(List.of("Authorization"));
        cfg.setAllowCredentials(true);
        org.springframework.web.cors.UrlBasedCorsConfigurationSource source =
                new org.springframework.web.cors.UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", cfg);
        return source;
    }

    @Bean
    public org.springframework.security.crypto.password.PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public MethodSecurityExpressionHandler expressionHandler() {
        DefaultMethodSecurityExpressionHandler h = new DefaultMethodSecurityExpressionHandler();
        return h;
    }

}
