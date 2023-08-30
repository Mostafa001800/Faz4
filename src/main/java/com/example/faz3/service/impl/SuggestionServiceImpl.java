package com.example.faz3.service.impl;

import com.example.faz3.entity.Suggestion;
import com.example.faz3.entity.enu.StatusOrder;
import com.example.faz3.exception.SaveException;
import com.example.faz3.repository.SuggestionRepository;
import com.example.faz3.service.SuggestionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuggestionServiceImpl implements SuggestionService {
    private final SuggestionRepository repository;

    public SuggestionServiceImpl(SuggestionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(Suggestion suggestion) {
        if (duplicateRequest(suggestion) == false)
            if (checkPrice(suggestion) == false) {
                if (checkDate(suggestion) == false) {
                    if (checkStatusOrder(suggestion) == false) {
                        repository.save(suggestion);
                    } else {
                        throw new SaveException("You cannot submit a request");
                    }
                } else {
                    throw new SaveException("The date cannot be earlier than the order date");
                }
            } else {
                throw new SaveException("The suggested number is greater than the base number");
            }
    }

    @Override
    public boolean duplicateRequest(Suggestion suggestion) {
        List<Suggestion> list = suggestion.getOrder().getSuggestion();
        boolean test = false;
        for (Suggestion s : list) {
            if (s.getExpert() == suggestion.getExpert()) {
                test = true;
            }
        }
        return test;
    }

    @Override
    public boolean checkPrice(Suggestion suggestion) {
        boolean test = false;
        if (suggestion.getPrice() > suggestion.getOrder().getSubService().getBasePrice()) {
            test = true;
        }
        return test;
    }

    @Override
    public boolean checkDate(Suggestion suggestion) {
        boolean test = false;
        if (suggestion.getDate().isBefore(suggestion.getOrder().getDate())) {
            test = true;
        }
        return test;
    }

    @Override
    public boolean checkStatusOrder(Suggestion suggestion) {
        boolean test = false;
        if (suggestion.getOrder().getStatusOrder() == StatusOrder.ExpertSuggestions || suggestion.getOrder().getStatusOrder() == StatusOrder.ExpertSelection) {
            test = true;
        }
        return test;
    }
}

