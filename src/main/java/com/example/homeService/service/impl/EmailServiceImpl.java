package com.example.homeService.service.impl;

import com.example.homeService.entity.enu.UserRole;
import com.example.homeService.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;

    @Override
    public void sendEmail(SimpleMailMessage email) {
        mailSender.send(email);
    }

    @Override
    public SimpleMailMessage createEmail(String toEmail, String token, UserRole role) {
        String accountType = role.name();
        String text = "please click here : "
                + "http://localhost:8080/register/confirm?token=";
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(toEmail);
        mailMessage.setFrom("mostafagholipore@gmail.com");
        mailMessage.setSubject("Finish singup for " + accountType);
        mailMessage.setText(text + token);
        return mailMessage;
    }
}
