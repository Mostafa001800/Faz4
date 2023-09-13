package com.example.faz3.mapper;

import com.example.faz3.dto.expert.ExpertDto;
import com.example.faz3.entity.Expert;
import com.example.faz3.entity.enu.StatusExpert;
import com.example.faz3.entity.enu.UserRole;

public class ExpertMapper {
    public Expert convert(ExpertDto expertDto){
        Expert expert=new Expert();
        expert.setFirstName(expertDto.getFirstName());
        expert.setLastName(expertDto.getLastName());
        expert.setUsername(expertDto.getUsername());
        expert.setPassword(expertDto.getPassword());
        expert.setEmail(expertDto.getEmail());
        expert.setUserRole(UserRole.EXPERT);
        expert.setStatusExpert(StatusExpert.waiting);
        return expert;
    }
    public ExpertDto convert(Expert expert){
        ExpertDto expertDto=new ExpertDto();
        expertDto.setFirstName(expert.getFirstName());
        expertDto.setLastName(expert.getLastName());
        expertDto.setUsername(expert.getUsername());
        expertDto.setPassword(expert.getPassword());
        expertDto.setEmail(expert.getEmail());
        return expertDto;
    }
}
