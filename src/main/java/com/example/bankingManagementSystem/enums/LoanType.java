package com.example.bankingManagementSystem.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum LoanType {

    PERSONAL("personal"),

    HOME("home"),

    AUTO("auto"),

    STUDENT("student"),

    BUSINESS("business"),

    HOME_EQUITY("home_equity"),

    AUTO_TITLE("auto_title"),

    PAYDAY("payday"),

    CREDIT_CARD_CASH_ADVANCE("credit_card_cash_advance"),

    SECURED("secured"),

    UNSECURED("unsecured"),

    DEBT_CONSOLIDATION("debt_consolidation"),

    CONSTRUCTION("construction"),

    BRIDGE("bridge"),

    REFINANCE("refinance"),

    SBA("sba"),

    EQUIPMENT("equipment"),

    MEDICAL("medical"),

    VACATION("vacation"),

    OTHER("other");

    private final String value;

    LoanType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static LoanType fromValue(String text) {
        for (LoanType type : LoanType.values()) {
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
