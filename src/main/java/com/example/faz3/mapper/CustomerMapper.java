package com.example.faz3.mapper;

import com.example.faz3.dto.CustomerDto;
import com.example.faz3.entity.Customer;

public class CustomerMapper {

    public Customer convert(CustomerDto customerDto){
        Customer customer=new Customer();
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setUsername(customerDto.getUsername());
        customer.setPassword(customerDto.getPassword());
        customer.setEmail(customerDto.getEmail());
        return customer;
    }
}
