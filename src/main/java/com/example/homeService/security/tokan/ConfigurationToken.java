package com.example.homeService.security.tokan;

import com.example.homeService.base.domain.BaseEntity;
import com.example.homeService.base.domain.Person;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDateTime;
@Entity
@Data
public class ConfigurationToken extends BaseEntity<Long> {
    @Column(nullable = false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public ConfigurationToken(String token, LocalDateTime createdAt, LocalDateTime expiresAt, Person person) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.person = person;
    }

    public ConfigurationToken() {

    }
    public ConfigurationToken(LocalDateTime createdAt, LocalDateTime expiresAt, Person person) {
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.person = person;
    }
}
