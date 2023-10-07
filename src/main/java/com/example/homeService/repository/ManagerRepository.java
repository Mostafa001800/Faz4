package com.example.homeService.repository;

import com.example.homeService.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ManagerRepository extends JpaRepository<Manager,Long> {
     Optional<Manager> findByUsername(String user);
     Optional<Manager> findByEmail(String email);

}
