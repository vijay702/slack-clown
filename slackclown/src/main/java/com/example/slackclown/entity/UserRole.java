package com.example.slackclown.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UserRole {

    SUPERUSER("superuser"),

    ADMIN("admin"),

    ONDC_ADMIN("ondc_admin"),

    USER("user");

    private final String value;

    UserRole(String value) {
        this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
        return this.value;
    }

    @JsonCreator
    public static UserRole fromValue(String text) {
        for (UserRole b : UserRole.values()) {
            if (String.valueOf(b.value).equals(text)) {
                return b;
            }
        }
        return null;
    }

}
