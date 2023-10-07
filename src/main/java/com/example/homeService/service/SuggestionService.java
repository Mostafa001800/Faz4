package com.example.homeService.service;

import com.example.homeService.entity.Suggestion;

public interface SuggestionService {
    public void save(Suggestion suggestion);
    public boolean duplicateRequest(Suggestion suggestion);
    public boolean checkPrice(Suggestion suggestion);
    public boolean checkDate(Suggestion suggestion);
    public boolean checkStatusOrder(Suggestion suggestion);
}
