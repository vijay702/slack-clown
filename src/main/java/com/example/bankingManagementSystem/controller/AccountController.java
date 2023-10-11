package com.example.bankingManagementSystem.controller;

import com.example.bankingManagementSystem.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class AccountController {

    @Autowired
    AccountService accountService;


    @PostMapping("/deposit/{accountNumber}")
    public Object deposit(@PathVariable String accountNumber, @RequestParam BigDecimal amount) {
        return accountService.deposit(accountNumber, amount);
    }

}
