package com.example.bankingManagementSystem.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailSender {
    private static final String noReplyEmail = "vijayvijays763193@gmail.com";

    public static void sendNoReplyEmail(JavaMailSender mailSender, String toAddress, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(noReplyEmail);
        message.setTo(toAddress);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}