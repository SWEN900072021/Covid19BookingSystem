package com.example.covid19bookingsystem.domain;

import com.example.covid19bookingsystem.utils.EnumUtils.AccountType;
import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
public class Account {

    @NonNull
    private String username;

    @NonNull
    private String password;

    @NonNull
    private AccountType accountType;

}
