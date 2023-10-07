package com.example.homeService.service.impl;

import com.example.homeService.entity.RequestExpert;
import com.example.homeService.entity.enu.StatusExpert;
import com.example.homeService.repository.RequestExpertRepository;
import com.example.homeService.service.RequestExpertService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RequestExpertServiceImpl implements RequestExpertService {
private final RequestExpertRepository repository;
@Override
    public List<RequestExpert> findStatusWaiting(){
        List<RequestExpert> byStatusExpert = repository.findByStatusExpert(StatusExpert.waiting);
            return byStatusExpert;
    }
    @Transactional
    @Override
    public void save(RequestExpert requestExpert){
        repository.save(requestExpert);
    }
    @Override
    public Optional<RequestExpert> findById(Long Id){
        Optional<RequestExpert> byId = repository.findById(Id);
        return byId;
    }
}
