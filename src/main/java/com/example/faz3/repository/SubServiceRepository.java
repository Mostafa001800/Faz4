package com.example.faz3.repository;

import com.example.faz3.entity.SubService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubServiceRepository extends JpaRepository<SubService,Long> {
    Optional<SubService> findByTitle(String title);
}
