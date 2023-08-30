package com.example.faz3.service.impl;

import com.example.faz3.entity.RequestExpert;
import com.example.faz3.entity.enu.StatusExpert;
import com.example.faz3.repository.RequestExpertRepository;
import com.example.faz3.service.RequestExpertService;
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
