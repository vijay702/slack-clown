package com.example.slackclown.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private String firstName;

    private String lastName;

    private String fullName;

    private LocalDateTime dob;

    private String password;

    private String otp;

    private MultipartFile profilePictureData;

    private String profilePicture;

    private String statusUpdate;

    private String email;

    private String mobile;

    private String aboutMe;

    private String bio;

    private Boolean away;
}
