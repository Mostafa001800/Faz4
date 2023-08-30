package com.example.faz3.repository;

import com.example.faz3.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager,Long> {
    public Optional<Manager> findByUsername(String user);
}
