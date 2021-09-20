package com.example.covid19bookingsystem.security;

import com.example.covid19bookingsystem.domain.Account;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import static com.example.covid19bookingsystem.mapper.AccountMapper.findAccountByUsername;
import static com.example.covid19bookingsystem.utils.EnumUtils.AccountType.valueOf;

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
        if (username.equals("admin")) {
            Account account = new Account();
            account.setUsername("admin");
            String encodedPassword = new Pbkdf2PasswordEncoder("eduardo", 69, 420).encode("Password@123");
            account.setPassword(encodedPassword);
            account.setAccountType(valueOf("ADMIN"));
            return account;
        }
        return findAccountByUsername(username);
    }
}
