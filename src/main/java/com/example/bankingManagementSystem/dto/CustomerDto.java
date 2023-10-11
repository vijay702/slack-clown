package com.example.bankingManagementSystem.dto;

import com.example.bankingManagementSystem.enums.AccountType;
import com.example.bankingManagementSystem.enums.CustomerType;
import com.example.bankingManagementSystem.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

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

    private Status status;

    private String password;

    private String otp;

    private CustomerType customerType;

    private AccountType accountType;

}
