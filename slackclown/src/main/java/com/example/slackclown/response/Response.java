package com.example.slackclown.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class Response {

    public static ResponseEntity<Map<String, Object>> genericResponse(HttpStatus status, String message, Object data) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", message);
        body.put("data", data);
        return ResponseEntity.status(status).body(body);
    }

    public static ResponseEntity<Map<String, Object>> successResponse( String message, Object data) {
        return genericResponse(HttpStatus.OK, message, data);
    }

    public static ResponseEntity<Map<String, Object>> OK() {
        return genericResponse(HttpStatus.OK, "OK", null);
    }

    public static ResponseEntity<Map<String, Object>> BAD_REQUEST(String message) {
        return genericResponse(HttpStatus.BAD_REQUEST, message, null);
    }

    public static ResponseEntity<Map<String, Object>> NOT_FOUND(String message) {
        return genericResponse(HttpStatus.NOT_FOUND, message, null);
    }

    public static ResponseEntity<Map<String, Object>> UNAUTHORIZED(String message) {
        return genericResponse(HttpStatus.UNAUTHORIZED, message, null);
    }

}
