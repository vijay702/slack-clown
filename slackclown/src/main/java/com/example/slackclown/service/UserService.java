package com.example.slackclown.service;

import com.example.slackclown.dto.EmailDto;
import com.example.slackclown.dto.ResetPasswordDto;
import com.example.slackclown.dto.UserDto;

public interface UserService {
    Object createUser(UserDto userDto);

    Object userLogin(UserDto userDto);

    Object updateUser(Long id, UserDto userDto);

    Object sendOtp(EmailDto emailDto);

    Object resetPasswordWithOtp(ResetPasswordDto resetPasswordDto);

    Object resetPasswordWithOldPassword(ResetPasswordDto resetPasswordDto, String usernameFromJwt);
}
