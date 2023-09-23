package com.example.slackclown.service;

import com.example.slackclown.dto.EmailDto;
import com.example.slackclown.dto.ResetPasswordDto;
import com.example.slackclown.dto.UserDto;

public interface UserService {
    Object createUser(UserDto userDto);

    Object sendOtp(EmailDto emailDto);

    Object resetPassword(ResetPasswordDto resetPasswordDto);
}
