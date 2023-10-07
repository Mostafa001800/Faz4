package com.example.homeService.mapper;

import com.example.homeService.base.domain.Person;
import com.example.homeService.dto.CustomerDto;
import com.example.homeService.entity.Customer;
import com.example.homeService.entity.enu.UserRole;

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
    public CustomerDto convert(Person person){
        CustomerDto customerDto=new CustomerDto();
        customerDto.setFirstName(person.getFirstName());
        customerDto.setLastName(person.getLastName());
        customerDto.setUsername(person.getUsername());
        customerDto.setPassword(person.getPassword());
        customerDto.setEmail(person.getEmail());
        return customerDto;
    }

}
