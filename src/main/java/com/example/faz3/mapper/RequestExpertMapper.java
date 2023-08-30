package com.example.faz3.mapper;

import com.example.faz3.dto.RequestExpertDto;
import com.example.faz3.entity.RequestExpert;
import com.example.faz3.service.ExpertService;
import com.example.faz3.service.SubServiceService;
import com.example.faz3.service.impl.ExpertServiceImpl;
import com.example.faz3.service.impl.SubServiceServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RequiredArgsConstructor
public class RequestExpertMapper {
    private final SubServiceService subServiceService;
    private final ExpertService expertService;
    public RequestExpert convert(RequestExpertDto requestExpertDto){
        RequestExpert requestExpert=new RequestExpert();
        requestExpert.setExpert(expertService.findByUsername(requestExpertDto.getExpertUsername()).get());
        requestExpert.setSubService(subServiceService.findByTitle(requestExpertDto.getSubServiceTitle()).get());
        return requestExpert;
    }
}
