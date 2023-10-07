package com.example.homeService.mapper;

import com.example.homeService.dto.SuggestionDto;
import com.example.homeService.entity.Suggestion;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SuggestionMapper {

    public Suggestion convert(SuggestionDto suggestionDto) {
        Suggestion suggestion = new Suggestion();
        suggestion.setDate(suggestionDto.getDate());
        suggestion.setDurationOfWork(suggestionDto.getDurationOfWork());
        suggestion.setPrice(suggestionDto.getPrice());
        return suggestion;
    }
    public SuggestionDto convert(Suggestion suggestion) {
        SuggestionDto suggestionDto = new SuggestionDto();
        suggestionDto.setDate(suggestion.getDate());
        suggestionDto.setDurationOfWork(suggestion.getDurationOfWork());
        suggestionDto.setPrice(suggestion.getPrice());
        return suggestionDto;
    }

}
