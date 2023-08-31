package com.example.faz3.service;

import com.example.faz3.dto.manager.SubServiceDto;
import com.example.faz3.entity.SubService;

import java.util.Optional;

public interface SubServiceService {
    void update(SubService subService);
    void save(SubServiceDto subServiceDto);
    void deleteById(Long id);
    boolean repetitive(SubService subService);
    Optional<SubService> findById(Long id);
    Optional<SubService> findByTitle(String title);

}
