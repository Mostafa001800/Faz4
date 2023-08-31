package com.example.faz3.filter;

import com.example.faz3.dto.FilterDto;
import com.example.faz3.dto.ListFilterDto;
import com.example.faz3.entity.Expert;
import com.example.faz3.entity.SubService;
import com.example.faz3.mapper.FilterDtoMapper;

import java.util.ArrayList;
import java.util.List;

public class ExpertFilter {
    FilterDtoMapper filterDtoMapper=new FilterDtoMapper();

    public ListFilterDto filter(List<Expert> list, FilterDto filterDto){
        List<FilterDto> filterDtos=new ArrayList<>();
        List<Expert> filterList=list;
        if(filterDto.getFirstName()!=null){
            List<Expert> list1 = filterFirstname(filterList, filterDto.getFirstName());
            filterList=list1;
        }
        if (filterDto.getLastName()!=null){
            List<Expert> list1 = filterLastname(filterList, filterDto.getLastName());
            filterList=list1;
        }
        if (filterDto.getEmail()!=null){
            List<Expert> list1 = filterEmail(filterList, filterDto.getEmail());
            filterList=list1;
        }
        if (filterDto.getScore()>0){
            List<Expert> list1 = filterScore(filterList, filterDto.getScore());
            filterList=list1;
        }
        if (filterDto.getSubServiceTitle()!=null){
            List<Expert> list1 = filterSubServiceTitle(filterList, filterDto.getSubServiceTitle());
            filterList=list1;
        }

        for (Expert customer : filterList){
            FilterDto convert = filterDtoMapper.convert(customer);
            filterDtos.add(convert);
        }
        return new ListFilterDto(filterDtos);
    }








    public List<Expert> filterFirstname(List<Expert> experts, String filter){
        List<Expert> list=new ArrayList<>();
        for (Expert expert : experts){
            if(expert.getFirstName().contains(filter)){
                list.add(expert);
            }
        }
        return list;
    }
    public List<Expert> filterLastname(List<Expert> experts,String filter){
        List<Expert> list=new ArrayList<>();
        for (Expert expert : experts){
            if(expert.getLastName().contains(filter)){
                list.add(expert);
            }
        }
        return list;
    }
    public List<Expert> filterEmail(List<Expert> experts,String filter){
        List<Expert> list=new ArrayList<>();
        for (Expert expert : experts){
            if(expert.getEmail().contains(filter)){
                list.add(expert);
            }
        }
        return list;
    }
    public List<Expert> filterScore(List<Expert> experts,double filter){
        List<Expert> list=new ArrayList<>();
        for (Expert expert : experts){
            if(expert.getScore()==filter){
                list.add(expert);
            }
        }
        return list;
    }
    public List<Expert> filterSubServiceTitle(List<Expert> experts,String filter){
        List<Expert> list=new ArrayList<>();
        for (Expert expert : experts){
            for (SubService subService:expert.getSubServices()) {
                if(subService.getTitle().equals(filter)){
                    list.add(expert);
                }
            }
        }
        return list;
    }
}
