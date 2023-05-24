package com.luv2code.springboot.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {


    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }
/*  Removing this code as we don't use hardcoded users , configuring users from JDBC
@Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails saarthak = User.builder()
                .username("saarthak")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();
        UserDetails jack = User.builder()
                .username("jack")
                .password("{noop}test1234")
                .roles("EMPLOYEE", "MANAGER")
                .build();
        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test1235")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(saarthak, jack, john);
    }*/
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer ->
                configurer.requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.POST, "/api/employees/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/employees").hasRole("ADMIN")

        );

        //use http basic authentication
        http.httpBasic();
        //disable csrf
        http.csrf().disable();

        return http.build();

    }
}
