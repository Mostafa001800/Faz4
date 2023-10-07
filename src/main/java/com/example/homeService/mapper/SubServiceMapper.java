package com.example.homeService.mapper;

import com.example.homeService.dto.manager.SubServiceDto;
import com.example.homeService.entity.SubService;

public class SubServiceMapper {
    public SubService convert(SubServiceDto subServiceDto){
        SubService subService=new SubService();
        subService.setTitle(subServiceDto.getTitle());
        subService.setBasePrice(subServiceDto.getBasePrice());
        return subService;
    }
}
