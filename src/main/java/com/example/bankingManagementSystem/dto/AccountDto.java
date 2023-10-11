package com.example.bankingManagementSystem.dto;

import com.example.bankingManagementSystem.entity.Customer;
import com.example.bankingManagementSystem.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDto {

    private AccountType accountType;

    private BigDecimal accountDecimal;

    private Long accountId;

    private Customer customer;

    private String accountNumber;

}
