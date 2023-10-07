package com.example.homeService.repository;

import com.example.homeService.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<Service,Long> {
    Optional<Service> findByTitle(String title);
}
