package com.example.faz3.service;

import com.example.faz3.entity.enu.UserRole;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    void sendEmail(SimpleMailMessage email);

    SimpleMailMessage createEmail(String toEmail, String confirmationToken, UserRole role);

}
