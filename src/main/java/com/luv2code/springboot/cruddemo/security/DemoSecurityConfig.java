package com.luv2code.springboot.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails saarthak = User.builder()
                .username("saarthak")
                .password("test123")
                .roles("EMPLOYEE")
                .build();

        UserDetails jack = User.builder()
                .username("jack")
                .password("test123")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails john = User.builder()
                .username("john")
                .password("test123")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(saarthak, jack, john);
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer ->
                configurer.requestMatchers(HttpMethod.GET, "api/employees").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "api/employees/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.POST, "api/employees/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "api/employees").hasRole("ADMIN")

        );

        //use http basic authentication
        http.httpBasic();
        //disable csrf
        http.csrf().disable();

        return http.build();

    }
}
