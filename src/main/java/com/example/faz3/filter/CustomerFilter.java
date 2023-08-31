package com.example.faz3.filter;

import com.example.faz3.dto.FilterDto;
import com.example.faz3.dto.ListFilterDto;
import com.example.faz3.entity.Customer;
import com.example.faz3.mapper.FilterDtoMapper;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class CustomerFilter {
    FilterDtoMapper filterDtoMapper=new FilterDtoMapper();
    public ListFilterDto filter(List<Customer> list, FilterDto filterDto){
        List<FilterDto> filterDtos=new ArrayList<>();
        List<Customer> filterList=list;
        if(filterDto.getFirstName()!=null){
            List<Customer> list1 = filterFirstname(filterList, filterDto.getFirstName());
            filterList=list1;
        }
        if (filterDto.getLastName()!=null){
            List<Customer> list1 = filterLastname(filterList, filterDto.getLastName());
            filterList=list1;
        }
        if (filterDto.getEmail()!=null){
            List<Customer> list1 = filterEmail(filterList, filterDto.getEmail());
            filterList=list1;
        }
        for (Customer customer : filterList){
            FilterDto convert = filterDtoMapper.convert(customer);
            filterDtos.add(convert);
        }
        return new ListFilterDto(filterDtos);
    }

    public List<Customer> filterFirstname(List<Customer> customers,String filter){
        List<Customer> list=new ArrayList<>();
        for (Customer customer : customers){
            if(customer.getFirstName().contains(filter)){
                list.add(customer);
            }
        }
        return list;
    }
    public List<Customer> filterLastname(List<Customer> customers,String filter){
        List<Customer> list=new ArrayList<>();
        for (Customer customer : customers){
            if(customer.getLastName().contains(filter)){
                list.add(customer);
            }
        }
        return list;
    }
    public List<Customer> filterEmail(List<Customer> customers,String filter){
        List<Customer> list=new ArrayList<>();
        for (Customer customer : customers){
            if(customer.getEmail().contains(filter)){
                list.add(customer);
            }
        }
        return list;
    }

}
