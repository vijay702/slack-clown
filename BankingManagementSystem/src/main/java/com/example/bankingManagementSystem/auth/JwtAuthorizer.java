package com.example.bankingManagementSystem.auth;

import com.example.bankingManagementSystem.enums.UserRole;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface JwtAuthorizer {

    boolean required() default true;

    UserRole[] authorized() default {UserRole.SUPERUSER, UserRole.ADMIN, UserRole.USER};

}
