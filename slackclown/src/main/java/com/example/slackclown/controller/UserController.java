package com.example.slackclown.controller;

import com.example.slackclown.dto.UserDto;
import com.example.slackclown.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @Autowired
    UserService userService;


    @PostMapping("/signup")
    public Object userSignUp(UserDto userDto) {
        return userService.createUser(userDto);
    }
}
