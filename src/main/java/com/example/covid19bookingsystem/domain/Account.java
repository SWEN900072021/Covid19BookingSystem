package com.example.covid19bookingsystem.domain;

import com.example.covid19bookingsystem.utils.EnumUtils.AccountType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {

    private String username;

    private String password;

    private AccountType accountType;

}
