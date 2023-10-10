package com.example.bankingManagementSystem.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TransactionType {

    DEPOSIT("deposit"),

    WITHDRAWAL("withdrawal"),

    TRANSFER("transfer"),

    PAYMENT("payment"),

    PURCHASE("purchase"),

    LOAN_PAYMENT("loan_payment"),

    INTEREST_PAYMENT("interest_payment"),

    TRANSFER_BETWEEN_BANKS("transfer_between_banks"),

    CURRENCY_EXCHANGE("currency_exchange"),

    ONLINE_BANKING_TRANSFER("online_banking_transfer"),

    BILL_PAY("bill_pay"),

    ATM_TRANSACTION("atm_transaction"),

    WIRE_TRANSFER("wire_transfer"),

    MOBILE_PAYMENT("mobile_payment"),

    DIRECT_DEPOSIT("direct_deposit"),

    STOP_PAYMENT("stop_payment"),

    OVERDRAFT("overdraft"),

    LOAN_ORIGINATION("loan_origination"),

    LOAN_DISBURSEMENT("loan_disbursement"),

    LOAN_REPAYMENT("loan_repayment");

    private final String value;

    TransactionType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static TransactionType fromValue(String text) {
        for (TransactionType type : TransactionType.values()) {
            if (type.value.equals(text)) {
                return type;
            }
        }
        return null;
    }

    @Override
    @JsonValue
    public String toString() {
        return this.value;
    }

}
