package com.example.bankingManagementSystem.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CustomerType {

    @JsonProperty("user")
    USER("user"),

    @JsonProperty("employee")
    EMPLOYEE("employee");

    private final String value;

    CustomerType(String value) {
        this.value = value;
    }

    @JsonCreator
    public static com.example.bankingManagementSystem.enums.CustomerType fromValue(String text) {
        for (com.example.bankingManagementSystem.enums.CustomerType b : com.example.bankingManagementSystem.enums.CustomerType.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
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
