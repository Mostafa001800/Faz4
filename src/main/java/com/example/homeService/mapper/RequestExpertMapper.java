package com.example.homeService.mapper;

import com.example.homeService.dto.RequestExpertDto;
import com.example.homeService.entity.RequestExpert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RequestExpertMapper {
//    private final SubServiceService subServiceService;
//    private final ExpertService expertService;

    public RequestExpert convert(RequestExpertDto requestExpertDto) {
        RequestExpert requestExpert = new RequestExpert();
//        requestExpert.setExpert(expertService.findByUsername(requestExpertDto.getExpertUsername()).get());
//        requestExpert.setSubService(subServiceService.findByTitle(requestExpertDto.getSubServiceTitle()).get());
        return requestExpert;
    }

    public RequestExpertDto convert(RequestExpert requestExpert) {
        RequestExpertDto requestExpertDto = new RequestExpertDto();
        requestExpertDto.setExpertUsername(requestExpert.getExpert().getUsername());
        requestExpertDto.setSubServiceTitle(requestExpert.getSubService().getTitle());
        return requestExpertDto;
    }
}
