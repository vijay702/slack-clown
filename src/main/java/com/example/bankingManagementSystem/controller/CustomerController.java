package com.example.bankingManagementSystem.controller;

import com.example.bankingManagementSystem.auth.JwtTokenValidation;
import com.example.bankingManagementSystem.dto.EmailDto;
import com.example.bankingManagementSystem.dto.ResetPasswordDto;
import com.example.bankingManagementSystem.dto.CustomerDto;
import com.example.bankingManagementSystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    JwtTokenValidation jwtTokenValidation;

    @PostMapping("/create_user")
    public Object createUser(@ModelAttribute CustomerDto customerDto){
        return customerService.createUser(customerDto);
    }

    @PostMapping("/customer_login")
    public Object customerLogin(@RequestBody CustomerDto customerDto){
        return customerService.customerLogin(customerDto);
    }

    @PutMapping("update/{id}")
    public Object updateCustomer(@PathVariable("id") Long id, @ModelAttribute CustomerDto customerDto){
        return customerService.updateCustomer(id, customerDto);
    }

    @PostMapping("/send-otp")
    public Object SendOtp(@RequestBody EmailDto emailDto) {
        return customerService.sendOtp(emailDto);
    }

    @PutMapping("/change-password")
    public Object resetPasswordWithOtp(@RequestBody ResetPasswordDto resetPasswordDto) {
        return customerService.resetPasswordWithOtp(resetPasswordDto);
    }

    @PutMapping("/reset-password")
    public Object resetPasswordWithOldPassword(@RequestBody ResetPasswordDto resetPasswordDto){
        String token = "";
        String usernameFromJwt = String.valueOf(jwtTokenValidation.validateToken(token));
        return customerService.resetPasswordWithOldPassword(resetPasswordDto, usernameFromJwt);
    }

}
