package com.example.covid19bookingsystem.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new AuthenticationService()).passwordEncoder(new Pbkdf2PasswordEncoder("eduardo", 69, 420));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/createVaccineRecipient", "/login").permitAll()
                .antMatchers("/home").hasAnyRole("ADMIN", "VR", "HCP")
                .antMatchers("/createHealthCareProvider").hasRole("ADMIN")
                .antMatchers("/addTimeslot").hasRole("HCP")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/hcp/**").hasRole("HCP")
                .antMatchers("/vr/**").hasRole("VR")
                .anyRequest().authenticated()
            .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/home", true)
            .and()
                .logout();
    }
}