package com.sparta.toysrus.dto;

import lombok.AllArgsConstructor;

import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponseDto {
    //(description = "응답 결과")
    private String result;
    //(description = "응답 메시지")
    private String message;
    //(description = "응답 데이터")
    private String username;
}