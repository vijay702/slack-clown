package com.example.bankingManagementSystem.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AccountType {

    SAVINGS("savings"),

    CHECKING("checking"),

    CERTIFICATE_OF_DEPOSIT("certificate_of_deposit"),

    MONEY_MARKET("money_market"),

    INDIVIDUAL_RETIREMENT("individual_retirement"),

    HEALTH_SAVINGS("health_savings"),

    BUSINESS("business"),

    JOINT("joint"),

    STUDENT("student"),

    TRUST("trust"),

    BROKERAGE("brokerage"),

    CUSTODIAL("custodial"),

    HIGH_YIELD_SAVINGS("high_yield_savings"),

    SPECIALTY("specialty");

    private final String value;

    AccountType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static AccountType fromValue(String text) {
        for (AccountType type : AccountType.values()) {
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
