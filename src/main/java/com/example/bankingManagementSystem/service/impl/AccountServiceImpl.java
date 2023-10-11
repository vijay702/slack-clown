package com.example.bankingManagementSystem.service.impl;

import com.example.bankingManagementSystem.entity.Account;
import com.example.bankingManagementSystem.repository.AccountRepository;
import com.example.bankingManagementSystem.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    public Object deposit(String accountNumber, BigDecimal amount) {
        Optional<Account> optionalAccount = accountRepository.findByAccountNumber(accountNumber);
        if (optionalAccount.isEmpty()){
            return HttpStatus.NOT_FOUND;
        }
        Account account = optionalAccount.get();
        BigDecimal newBalance = account.getAccountBalance().add(amount);
        account.setAccountBalance(newBalance);
        return accountRepository.save(account);
    }
}
