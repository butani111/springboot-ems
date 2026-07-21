package com.example.ems.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http

                .csrf(csrf -> csrf.disable()) // CSRF protection is primarily designed for browser-based applications using cookies and server-side sessions.

                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/login")
                        .permitAll()

                        .requestMatchers(HttpMethod.DELETE,
                                "/employees/**")
                        .hasRole("ADMIN")

                        .anyRequest()
                        .authenticated()

                )

                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    UserDetailsService userDetailsService() {
        UserDetails admin =
                User.withUsername("admin")
                        .password("{noop}admin123")
                        .roles("ADMIN")
                        .build();

        UserDetails user =
                User.withUsername("John")
                        .password("{noop}user123")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(admin, user);
    }
}
