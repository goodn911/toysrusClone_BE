package com.sparta.toysrus.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequestDto {

    private String name;

    private String username;

    private String domain;

    private String password;

    private String passwordCheck;

    private String phone;

    private String address;

}
