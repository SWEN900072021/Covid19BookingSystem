package com.example.covid19bookingsystem.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class AuthenticationInitializer extends AbstractSecurityWebApplicationInitializer {

    public AuthenticationInitializer() {
        super(SecurityConfig.class);
    }
}
