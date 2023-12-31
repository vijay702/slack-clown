package com.example.bankingManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResetPasswordDto {

    private String otp;

    private String newPassword;

    private String oldPassword;

    private String confirmPassword;
}
