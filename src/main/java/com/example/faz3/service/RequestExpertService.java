package com.example.faz3.service;

import com.example.faz3.entity.RequestExpert;

import java.util.List;
import java.util.Optional;

public interface RequestExpertService {
    List<RequestExpert> findStatusWaiting();
    void save(RequestExpert requestExpert);
    Optional<RequestExpert> findById(Long Id);
}
