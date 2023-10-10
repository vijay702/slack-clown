package com.example.bankingManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {

    private String name;

    private String address;

    private String phoneNumber;

    private Date dob;

    private String email;

    private MultipartFile profilePictureData;

    private String profilePicture;

    private String status;

    private String password;

    private String otp;

}
