package com.example.bankingManagementSystem.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {

    @JsonProperty("active")
    ACTIVE("active"),

    @JsonProperty("inactive")
    IN_ACTIVE("inactive"),

    @JsonProperty("deleted")
    DELETED("deleted");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    @JsonCreator
    public static Status fromValue(String text) {
        for (Status b : Status.values()) {
            if (b.name().equals(text)) {
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
