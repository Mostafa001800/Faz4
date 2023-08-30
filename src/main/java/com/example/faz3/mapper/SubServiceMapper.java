package com.example.faz3.mapper;

import com.example.faz3.dto.manager.SubServiceDto;
import com.example.faz3.entity.SubService;

public class SubServiceMapper {
    public SubService convert(SubServiceDto subServiceDto){
        SubService subService=new SubService();
        subService.setTitle(subServiceDto.getTitle());
        subService.setBasePrice(subServiceDto.getBasePrice());
        return subService;
    }
}
