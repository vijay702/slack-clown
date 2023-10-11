package com.example.bankingManagementSystem.service;

import com.example.bankingManagementSystem.dto.EmailDto;
import com.example.bankingManagementSystem.dto.ResetPasswordDto;
import com.example.bankingManagementSystem.dto.CustomerDto;

public interface CustomerService {

    Object createUser(CustomerDto customerDto);

    Object customerLogin(CustomerDto customerDto);

    Object updateCustomer(Long id, CustomerDto customerDto);

    Object sendOtp(EmailDto emailDto);

    Object resetPasswordWithOtp(ResetPasswordDto resetPasswordDto);

    Object resetPasswordWithOldPassword(ResetPasswordDto resetPasswordDto, String usernameFromJwt);
}
