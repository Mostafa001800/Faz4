package com.example.homeService.service;

import com.example.homeService.entity.enu.UserRole;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    void sendEmail(SimpleMailMessage email);

    SimpleMailMessage createEmail(String toEmail, String confirmationToken, UserRole role);

}
