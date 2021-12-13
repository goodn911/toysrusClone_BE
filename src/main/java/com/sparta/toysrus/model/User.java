package com.sparta.toysrus.model;

import com.sparta.toysrus.dto.SignupRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String address;

    public User(SignupRequestDto requestDto){
        this.name= requestDto.getName();
        this.email=requestDto.getUsername()+"@"+requestDto.getDomain();
        this.password=requestDto.getPassword();
        this.phone=requestDto.getPhone();
        this.address=requestDto.getAddress();
    }
}
