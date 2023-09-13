package com.example.faz3.service;

import com.example.faz3.dto.ListOrderDto;
import com.example.faz3.dto.RequestExpertDto;
import com.example.faz3.dto.SuggestionDto;
import com.example.faz3.dto.expert.ExpertDto;
import com.example.faz3.entity.Comment;
import com.example.faz3.entity.Expert;
import com.example.faz3.entity.Order;

import java.util.List;
import java.util.Optional;

public interface ExpertService {
    Optional<Expert> login(String user, String pass);
    ListOrderDto works(Expert expert);
    void chanelPassword(Expert expert, String pass);
    void singUp(ExpertDto expertDto);
    void newSingUp(ExpertDto expertDto);
    void saveImage(Expert expert, String location);
    void registerTheOffer(SuggestionDto suggestionDto);
    void requestExpert(RequestExpertDto requestExpertDto);
    Optional<Expert> findById(Long id);
    Optional<Expert> findByUsername(String user);
    boolean isExpertList(String subService, String expertUser);
    void update(Expert expert);
    void updateScore(Comment comment);
    double showScore(String expertUsername);
    List<Expert> findAll();
    Optional<Expert> findByEmail(String email);






}
