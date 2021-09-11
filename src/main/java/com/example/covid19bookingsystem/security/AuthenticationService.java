package com.example.covid19bookingsystem.security;

import com.example.covid19bookingsystem.domain.Account;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AuthenticationService implements UserDetailsService {

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account user = findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        User.UserBuilder builder = User.withUsername(user.getUsername());
        builder.password(user.getPassword());
        builder.roles(user.getAccountType().toString());

        return builder.build();
    }

    private Account findByUsername(String username) {
        // TODO fetch user from DB
        Account account = new Account();
        if (username.equals("user")) {
            account.setUsername("user");
            account.setPassword("password");
        } else if (username.equals("admin")) {
            account.setUsername("admin");
            account.setPassword("password");
        }
        else {
            return null;
        }
        return account;
    }
}
