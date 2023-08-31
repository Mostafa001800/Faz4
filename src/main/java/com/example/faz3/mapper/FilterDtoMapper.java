package com.example.faz3.mapper;

import com.example.faz3.dto.FilterDto;
import com.example.faz3.dto.FilterEnum;
import com.example.faz3.entity.Customer;
import com.example.faz3.entity.Expert;

public class FilterDtoMapper {
    public FilterDto convert(Customer customer){
        FilterDto filterDto=new FilterDto();
        filterDto.setFirstName(customer.getFirstName());
        filterDto.setLastName(customer.getLastName());
        filterDto.setEmail(customer.getEmail());
        filterDto.setUsername(customer.getUsername());
        filterDto.setFilterEnum(FilterEnum.Customer);
        filterDto.setPassword(customer.getPassword());
        return filterDto;
    }
    public FilterDto convert(Expert expert){
        FilterDto filterDto=new FilterDto();
        filterDto.setFirstName(expert.getFirstName());
        filterDto.setLastName(expert.getLastName());
        filterDto.setEmail(expert.getEmail());
        filterDto.setUsername(expert.getUsername());
        filterDto.setFilterEnum(FilterEnum.Expert);
        filterDto.setPassword(expert.getPassword());
        filterDto.setScore(expert.getScore());
        filterDto.setSubServiceTitle(expert.getSubServices().get(0).getTitle());
        return filterDto;
    }
}
