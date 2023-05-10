package com.luv2code.springboot.cruddemo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
public class DemoSecurityConfig {

    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails saarthak = User.builder()
                .username("saarthak")
                .password("{noop}test@123")
                .build();

        return new InMemoryUserDetailsManager(saarthak);
    }
}
