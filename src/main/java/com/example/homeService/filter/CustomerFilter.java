package com.example.homeService.filter;

import com.example.homeService.dto.FilterDto;
import com.example.homeService.dto.ListFilterDto;
import com.example.homeService.entity.Customer;
import com.example.homeService.mapper.FilterDtoMapper;

import java.util.*;

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
    public List<Customer> filterByOrder(List<Customer> customers){
        Collections.sort(customers, new Comparator<Customer>() {
            @Override
            public int compare(Customer o1, Customer o2) {
                return o2.getOrders().size() - o1.getOrders().size();
            }
        });
        return customers;
    }
}
