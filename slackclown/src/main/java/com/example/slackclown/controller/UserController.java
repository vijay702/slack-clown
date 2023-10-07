package com.example.slackclown.controller;

import com.example.slackclown.auth.JwtAuthorizer;
import com.example.slackclown.auth.JwtTokenValidation;
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

    @Autowired
    JwtTokenValidation jwtTokenValidation;


    @PostMapping("/signup")
    public Object userSignUp(@ModelAttribute UserDto userDto) {
        return userService.createUser(userDto);
    }

    @PostMapping("/user_login")
    public Object userLogin(@RequestBody UserDto userDto){
        return userService.userLogin(userDto);
    }

    @PutMapping("update/{id}")
    public Object updateUser(@PathVariable("id") Long id, @ModelAttribute UserDto userDto){
        return userService.updateUser(id, userDto);
    }

    @PostMapping("/send-otp")
    public Object SendOtp(@RequestBody EmailDto emailDto) {
        return userService.sendOtp(emailDto);
    }

    @PutMapping("/change-password")
    public Object resetPasswordWithOtp(@RequestBody ResetPasswordDto resetPasswordDto) {
        return userService.resetPasswordWithOtp(resetPasswordDto);
    }

    @PutMapping("reset-password")
    public Object resetPasswordWithOldPassword(@RequestBody ResetPasswordDto resetPasswordDto){
        String token = "";
        String usernameFromJwt = String.valueOf(jwtTokenValidation.validateToken(token));
        return userService.resetPasswordWithOldPassword(resetPasswordDto, usernameFromJwt);
    }

}
