package com.example.faz3.service;

import com.example.faz3.dto.manager.SubServiceDto;
import com.example.faz3.entity.SubService;

import java.util.Optional;

public interface SubServiceService {
    public void update(SubService subService);
    public void save(SubServiceDto subServiceDto);
    public void deleteById(Long id);
    public boolean repetitive(SubService subService);
    public Optional<SubService> findById(Long id);
    public Optional<SubService> findByTitle(String title);

}
