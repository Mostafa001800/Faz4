package com.example.faz3.repository;

import com.example.faz3.entity.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ExpertRepository extends JpaRepository<Expert,Long> {
    public Optional<Expert> findByUsername(String user);
}
