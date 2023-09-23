package com.example.slackclown.service.impl;

import com.example.slackclown.converter.UserDtoConverter;
import com.example.slackclown.dto.EmailDto;
import com.example.slackclown.dto.ResetPasswordDto;
import com.example.slackclown.dto.UserDto;
import com.example.slackclown.email.EmailSender;
import com.example.slackclown.entity.AppUser;
import com.example.slackclown.repository.UserRepository;
import com.example.slackclown.service.UserService;
import com.example.slackclown.util.ImageUploaderUtill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;

/**
 * this service class is for creating slack
 * users and register purpose and user operations
 */
@Service
public class UserServiceImpl implements UserService {


    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private static final String USER = "user";

    @Autowired
    UserRepository userRepository;


    @Autowired
    UserDtoConverter userDtoConverter;

    @Autowired
    ImageUploaderUtill imageUploaderUtill;

    @Autowired
    JavaMailSender mailSender;


    @Override
    public Object createUser(UserDto userDto) {
        return userRepository.findByEmail(userDto.getEmail())
            .map(existingUser -> "User already exists")
            .orElseGet(() -> {
                imageUploaderUtill.imageUploader(userDto.getProfilePictureData(), USER);
                userRepository.save(userDtoConverter.dtoToEntity(userDto));
                return "user created sucessfully";
            });
    }

    @Override
    public Object sendOtp(EmailDto emailDto) {
        Optional<AppUser> optionalAppUser = userRepository.findByEmail(emailDto.getEmail());
        if (optionalAppUser.isEmpty()) {
            return "user is not present with this email";
        }
        AppUser appUser = optionalAppUser.get();
        /// have to replace with update query , all field update query
        appUser.setOtp(generateOtp());
        EmailSender.sendNoReplyEmail(mailSender, appUser.getEmail(), "VerificationOtp", generateOtp());
        userRepository.save(appUser);
        return "verification otp send to the user sucessfully";
    }

    @Override
    public Object resetPassword(ResetPasswordDto resetPasswordDto) {

        Optional<AppUser> optionalUserByOtp = userRepository.findByOtp(resetPasswordDto.getOtp());
        if (optionalUserByOtp.isEmpty() &&
            Objects.equals(resetPasswordDto.getNewPassword(), resetPasswordDto.getConfirmPassword())) {
            return "otp is not valid Or new password and confirm password are not same";
        }
        /// have to replace with update query , all field update query
        AppUser appUser = optionalUserByOtp.get();
        appUser.setPassword(resetPasswordDto.getNewPassword());
        userRepository.save(appUser);
        return "password changed sucessfully";
    }


    private String generateOtp() {
        Random random = new Random();
        // Generate an 8-digit random OTP
        int min = 10000000; // Smallest 8-digit number
        int max = 99999999; // Largest 8-digit number
        int otp = random.nextInt(max - min + 1) + min;
        return String.valueOf(otp);
    }
}
