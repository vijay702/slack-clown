package com.example.slackclown.service.impl;

import com.example.slackclown.auth.JwtTokenValidation;
import com.example.slackclown.converter.UserDtoConverter;
import com.example.slackclown.dto.EmailDto;
import com.example.slackclown.dto.ResetPasswordDto;
import com.example.slackclown.dto.UserDto;
import com.example.slackclown.email.EmailSender;
import com.example.slackclown.entity.AppUser;
import com.example.slackclown.repository.UserRepository;
import com.example.slackclown.response.Response;
import com.example.slackclown.service.UserService;
import com.example.slackclown.util.ImageUploaderUtill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    JwtTokenValidation jwtTokenValidation;


    @Override
    public Object createUser(UserDto userDto) {
        return userRepository.findByEmail(userDto.getEmail())
                .map(existingUser -> Response.BAD_REQUEST("User already exist"))
                .orElseGet(() -> {
                    String profilePictureData = imageUploaderUtill.imageUploader(userDto.getProfilePictureData(), USER);
                    AppUser appUser = userDtoConverter.dtoToEntity(userDto);
                    appUser.setProfilePicture(profilePictureData);
                    userRepository.save(appUser);
                    return Response.OK();
                });
    }

    @Override
    public Object userLogin(UserDto userDto) {
        Optional<AppUser> optionalAppUser = userRepository.findByEmail(userDto.getEmail());
        if (optionalAppUser.isEmpty()) {
            return Response.NOT_FOUND("User not found");
        }
        AppUser user = optionalAppUser.get();
        if (!user.getPassword().equals(userDto.getPassword())) {
            return HttpStatus.UNAUTHORIZED;
        }
        return jwtTokenValidation.generateToken(userDto.getEmail());
    }


    @Override
    public Object updateUser(Long id, UserDto userDto) {
        Optional<AppUser> optionalAppUser = userRepository.findById(id);
        if (optionalAppUser.isEmpty()) {
            return Response.NOT_FOUND("User not found");
        }
        userRepository.updateUser(userDto.getFirstName(), userDto.getLastName(), userDto.getFullName(),
                userDto.getDob(), userDto.getPassword(), userDto.getProfilePicture(), userDto.getEmail(),
                userDto.getMobile(), userDto.getAboutMe(), userDto.getBio(), userDto.getAway(), id);
        return Response.OK();
    }

    @Override
    public Object sendOtp(EmailDto emailDto) {
        Optional<AppUser> optionalAppUser = userRepository.findByEmail(emailDto.getEmail());
        if (optionalAppUser.isEmpty()) {
            return Response.NOT_FOUND("user is not present with this email");
        }
        AppUser appUser = optionalAppUser.get();
        /// have to replace with update query , all field update query
        appUser.setOtp(generateOtp());
        EmailSender.sendNoReplyEmail(mailSender, appUser.getEmail(), "VerificationOtp", generateOtp());
        userRepository.save(appUser);
        return ResponseEntity.ok("verification otp send to the user successfully");
    }

    @Override
    public Object resetPasswordWithOtp(ResetPasswordDto resetPasswordDto) {
        Optional<AppUser> optionalUserByOtp = userRepository.findByOtp(resetPasswordDto.getOtp());
        if (optionalUserByOtp.isEmpty() &&
                Objects.equals(resetPasswordDto.getNewPassword(), resetPasswordDto.getConfirmPassword())) {
            return Response.NOT_FOUND("otp is not valid Or new password and confirm password are not same");
        }
        /// have to replace with update query , all field update query
        AppUser appUser = optionalUserByOtp.get();
        appUser.setPassword(resetPasswordDto.getNewPassword());
        userRepository.save(appUser);
        return Response.OK();
    }

    @Override
    public Object resetPasswordWithOldPassword(ResetPasswordDto resetPasswordDto, String usernameFromJwt) {
        Optional<AppUser> optionalAppUser = userRepository.findByEmail(usernameFromJwt);
        if (optionalAppUser.isEmpty()) {
            return Response.NOT_FOUND("User not found");
        }
        AppUser appUser = optionalAppUser.get();
        if (!Objects.equals(resetPasswordDto.getOldPassword(), appUser.getPassword())){
            return Response.BAD_REQUEST("The Old password does not exist!");
        }
        if (!Objects.equals(resetPasswordDto.getNewPassword(), resetPasswordDto.getConfirmPassword())) {
            return Response.NOT_FOUND("The New password and confirm password are not same");
        }
        appUser.setPassword(resetPasswordDto.getNewPassword());
        userRepository.save(appUser);
        return Response.OK();
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
