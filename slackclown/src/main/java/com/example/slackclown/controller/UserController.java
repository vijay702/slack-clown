package com.example.slackclown.controller;

import com.example.slackclown.dto.EmailDto;
import com.example.slackclown.dto.ResetPasswordDto;
import com.example.slackclown.dto.UserDto;
import com.example.slackclown.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {


    @Autowired
    UserService userService;


    @PostMapping("/signup")
    public Object userSignUp(@ModelAttribute UserDto userDto) {
        return userService.createUser(userDto);
    }


    @PostMapping("/send-otp")
    public Object SendOtp(@RequestBody EmailDto emailDto) {
        return userService.sendOtp(emailDto);
    }

    @PutMapping("/reset-password")
    public Object resetPassword(@RequestBody ResetPasswordDto resetPasswordDto) {
        return userService.resetPassword(resetPasswordDto);
    }
}
