package com.example.bankingManagementSystem.entity;

import com.example.bankingManagementSystem.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "accounts")
public class Account implements Serializable {

    @Serial
    private static final long serialVersionUID = 987654321L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long accountId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "account_type")
    private AccountType accountType;

    @Column(name = "account_balance")
    private BigDecimal accountBalance;

    @Column(name = "account_number")
    private String accountNumber;

}
