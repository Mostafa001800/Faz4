package com.example.faz3.service;

import com.example.faz3.dto.RequestExpertDto;
import com.example.faz3.dto.expert.ExpertDto;
import com.example.faz3.entity.Expert;
import com.example.faz3.entity.Order;
import com.example.faz3.entity.RequestExpert;
import com.example.faz3.entity.Suggestion;

import java.util.List;
import java.util.Optional;

public interface ExpertService {
    public Optional<Expert> login(String user, String pass);
    public List<Order> works(Expert expert);
    public void chanelPassword(Expert expert, String pass);
    public void singUp(ExpertDto expertDto);
    public void saveImage(Expert expert, String location);
    public void RegisterTheTOffer(Suggestion suggestion);
    public void startJob(Expert expert, Long wordId);
    public void requestExpert(RequestExpertDto requestExpertDto);
    public Optional<Expert> findById(Long id);
    public Optional<Expert> findByUsername(String user);
    public boolean isExpertList(String subService, String expertUser);
    void update(Expert expert);








}
