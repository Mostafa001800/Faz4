package com.example.homeService.service;

import com.example.homeService.entity.RequestExpert;

import java.util.List;
import java.util.Optional;

public interface RequestExpertService {
    List<RequestExpert> findStatusWaiting();
    void save(RequestExpert requestExpert);
    Optional<RequestExpert> findById(Long Id);
}
