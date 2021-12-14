package com.sparta.toysrus.service;


import com.sparta.toysrus.dto.SignupRequestDto;
import com.sparta.toysrus.model.User;
import com.sparta.toysrus.repository.UserRepository;
import com.sparta.toysrus.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public String userRegister(SignupRequestDto signupRequestDto) {

        String username = signupRequestDto.getUsername()+"@"+signupRequestDto.getDomain();
        Optional<User> foundEmail = userRepository.findByUsername(username);
        UserValidator.checkEmail(foundEmail);

        UserValidator.checkPassword(signupRequestDto);
        String password = passwordEncoder.encode(signupRequestDto.getPassword());

        User user = new User(signupRequestDto);
        user.setPassword(password);
        userRepository.save(user);


        return "회원가입 성공";
    }
}
