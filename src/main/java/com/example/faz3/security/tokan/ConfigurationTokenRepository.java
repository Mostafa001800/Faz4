package com.example.faz3.security.tokan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
@Repository
public interface ConfigurationTokenRepository extends JpaRepository<ConfigurationToken,Long> {
    Optional<ConfigurationToken> findByToken(String token);
    @Transactional
    @Modifying
    @Query("UPDATE ConfigurationToken c SET c.confirmedAt = ?2 WHERE c.token = ?1")
    int updateConfirmedAt(String token, LocalDateTime localDateTime);
}
