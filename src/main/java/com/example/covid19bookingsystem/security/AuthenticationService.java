package com.example.covid19bookingsystem.security;

import com.example.covid19bookingsystem.domain.Account;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import static com.example.covid19bookingsystem.utils.EnumUtils.AccountType.valueOf;

public class AuthenticationService implements UserDetailsService {

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account user = findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        User.UserBuilder builder = User.withUsername(user.getUsername());
        //temporary
        String encodedPassword = new Pbkdf2PasswordEncoder("eduardo", 69, 420).encode(user.getPassword());
        builder.password(encodedPassword);
        //builder.password(user.getPassword());
        builder.roles(user.getAccountType().toString());
        return builder.build();
    }

    private Account findByUsername(String username) {
        // TODO fetch user from DB
        Account account = new Account();
        if (username.equals("vr")) {
            account.setUsername("vr");
            account.setPassword("password");
            account.setAccountType(valueOf("VR"));
        } else if (username.equals("admin")) {
            account.setUsername("admin");
            account.setPassword("password");
            account.setAccountType(valueOf("ADMIN"));
        } else if (username.equals("hcp")) {
            account.setUsername("hcp");
            account.setPassword("password");
            account.setAccountType(valueOf("HCP"));
        } else {
            return null;
        }
        return account;
    }
}
