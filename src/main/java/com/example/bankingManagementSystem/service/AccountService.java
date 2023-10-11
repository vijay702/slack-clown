package com.example.bankingManagementSystem.service;

import java.math.BigDecimal;

public interface AccountService {

    Object deposit(String accountNumber, BigDecimal amount);

}
