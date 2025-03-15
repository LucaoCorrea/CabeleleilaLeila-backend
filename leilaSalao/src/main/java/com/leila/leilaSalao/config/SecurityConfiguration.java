package com.leila.leilaSalao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.leila.leilaSalao.auth.JwtAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;
import static com.leila.leilaSalao.model.Permission.*;
import static com.leila.leilaSalao.model.Role.*;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

    private static final String[] WHITE_LIST_URL = {
            "/api/v1/auth/**", "/api/v1/appointments", "/api/v1/users", "/api/v1/appointments/{id}",
            "/v2/api-docs", "/v3/api-docs", "/v3/api-docs/**", "/swagger-resources",
            "/swagger-resources/**", "/configuration/ui", "/configuration/security",
            "/swagger-ui/**", "/webjars/**", "/swagger-ui.html"
    };

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(
                        request -> new org.springframework.web.cors.CorsConfiguration()
                                .applyPermitDefaultValues()))
                .csrf().disable()
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers(WHITE_LIST_URL).permitAll()
                        .requestMatchers("/api/appointments/user/**").hasAnyRole(ADMIN.name(), CLIENT.name()) // Permite ADMIN e CLIENT
                        .requestMatchers(POST, "/api/v1/management/**").hasRole(ADMIN.name()) // Apenas ADMIN pode fazer POST
                        .requestMatchers(GET, "/api/v1/management/**")
                        .hasAnyAuthority(ADMIN_READ.name(), CLIENT_READ.name()) // ADMIN e CLIENT podem ler
                        .requestMatchers(PUT, "/api/v1/management/**")
                        .hasAuthority(ADMIN_UPDATE.name()) // Apenas ADMIN pode fazer PUT
                        .requestMatchers(DELETE, "/api/v1/management/**")
                        .hasAuthority(ADMIN_DELETE.name()) // Apenas ADMIN pode fazer DELETE
                        .anyRequest().authenticated())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout -> logout.logoutUrl("/api/v1/auth/logout")
                        .addLogoutHandler(logoutHandler)
                        .logoutSuccessHandler(
                                (request, response, authentication) -> SecurityContextHolder
                                        .clearContext()));

        return http.build();
    }

    @Configuration
    public static class CorsConfig implements WebMvcConfigurer {

        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("http://localhost:4200")
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
        }
    }
}