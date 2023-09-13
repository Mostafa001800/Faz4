package com.example.faz3.repository;

import com.example.faz3.entity.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Repository
public interface ExpertRepository extends JpaRepository<Expert,Long> {
    Optional<Expert> findByUsername(String user);
    Optional<Expert> findByEmail(String email);
}
