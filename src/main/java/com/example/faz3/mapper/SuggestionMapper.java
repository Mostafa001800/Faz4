package com.example.faz3.mapper;

import com.example.faz3.dto.SuggestionDto;
import com.example.faz3.entity.Suggestion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SuggestionMapper {
//    private final ExpertService expertService;
//    private final OrderService orderService;

    public Suggestion convert(SuggestionDto suggestionDto) {
        Suggestion suggestion = new Suggestion();
//        suggestion.setExpert(expertService.findByUsername(suggestionDto.getExpertUsername()).get());
//        suggestion.setOrder(orderService.findById(suggestionDto.getOrderId()).get());
        suggestion.setDate(suggestionDto.getDate());
        suggestion.setDurationOfWork(suggestionDto.getDurationOfWork());
        suggestion.setPrice(suggestionDto.getPrice());
        return suggestion;
//        return null;
    }
}
