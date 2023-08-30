package com.example.faz3.repository;

import com.example.faz3.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<Service,Long> {
    Optional<Service> findByTitle(String title);
}
