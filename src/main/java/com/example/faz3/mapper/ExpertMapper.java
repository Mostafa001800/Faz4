package com.example.faz3.mapper;

import com.example.faz3.dto.expert.ExpertDto;
import com.example.faz3.entity.Expert;

public class ExpertMapper {
    public Expert convert(ExpertDto expertDto){
        Expert expert=new Expert();
        expert.setFirstName(expertDto.getFirstName());
        expert.setLastName(expertDto.getLastName());
        expert.setUsername(expertDto.getUsername());
        expert.setPassword(expertDto.getPassword());
        expert.setEmail(expertDto.getEmail());
        return expert;
    }
}
