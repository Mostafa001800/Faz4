package com.example.faz3.security.tokan;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ConfigurationTokenService {
    private final ConfigurationTokenRepository repository;

    public void saveConfigurationToken(ConfigurationToken configurationToken) {
        repository.save(configurationToken);
    }

    public Optional<ConfigurationToken> getToken(String token) {
        return repository.findByToken(token);
    }

    public void setConfirmedAt(String token) {
        repository.updateConfirmedAt(token, LocalDateTime.now());
    }
    public List<ConfigurationToken> findByConfirmedAt(LocalDateTime after, LocalDateTime before){
        return repository.findByConfirmedAt(after,before);
    }

}
