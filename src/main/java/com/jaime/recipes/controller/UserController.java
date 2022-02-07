package com.jaime.recipes.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.jaime.recipes.dto.NewUserDto;
import com.jaime.recipes.service.UserService;

import javax.validation.Valid;

@RestController
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/api/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid NewUserDto userDto) {
        userService.registerNewUser(userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()));
        return ResponseEntity.ok().build();
    }
}
