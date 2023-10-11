package com.example.bankingManagementSystem.entity;

import com.example.bankingManagementSystem.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "transaction")
public class Transaction implements Serializable {

    @Serial
    private static final long serialVersionUID = 987654321L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "transaction_type")
    private TransactionType transactionType;

    private BigDecimal amount;

    private Date timestamp;

}
