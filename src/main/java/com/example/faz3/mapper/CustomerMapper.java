package com.example.faz3.mapper;

import com.example.faz3.dto.CustomerDto;
import com.example.faz3.entity.Customer;
import com.example.faz3.entity.enu.UserRole;

public class CustomerMapper {

    public Customer convert(CustomerDto customerDto){
        Customer customer=new Customer();
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setUsername(customerDto.getUsername());
        customer.setPassword(customerDto.getPassword());
        customer.setEmail(customerDto.getEmail());
        customer.setUserRole(UserRole.CUSTOMER);
        return customer;
    }
    public CustomerDto convert(Customer customer){
        CustomerDto customerDto=new CustomerDto();
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setUsername(customer.getUsername());
        customerDto.setPassword(customer.getPassword());
        customerDto.setEmail(customer.getEmail());
        return customerDto;
    }

}
