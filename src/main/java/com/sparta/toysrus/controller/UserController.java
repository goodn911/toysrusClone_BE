package com.sparta.toysrus.controller;

import com.sparta.toysrus.dto.LoginResponseDto;
import com.sparta.toysrus.dto.SignupRequestDto;
import com.sparta.toysrus.security.UserDetailsImpl;
import com.sparta.toysrus.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/api/auth/signup")
    public String userRegister(@RequestBody SignupRequestDto signupRequestDto){
        userService.userRegister(signupRequestDto);
        return "회원가입 성공";

    }
    @GetMapping("/api/auth")
    public LoginResponseDto userLogin(@AuthenticationPrincipal UserDetailsImpl userDetails){
        System.out.println(userDetails.getUsername());
        return new LoginResponseDto("success","로그인성공",userDetails.getUsername());
    }


}
