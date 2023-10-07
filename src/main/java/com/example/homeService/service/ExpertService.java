package com.example.homeService.service;

import com.example.homeService.dto.ListOrderDto;
import com.example.homeService.dto.RequestExpertDto;
import com.example.homeService.dto.SuggestionDto;
import com.example.homeService.dto.expert.ExpertDto;
import com.example.homeService.entity.Comment;
import com.example.homeService.entity.Expert;

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
