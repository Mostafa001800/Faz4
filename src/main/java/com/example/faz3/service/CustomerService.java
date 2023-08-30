package com.example.faz3.service;

import com.example.faz3.dto.CustomerDto;
import com.example.faz3.dto.OrderDto;
import com.example.faz3.entity.Customer;
import com.example.faz3.entity.Order;
import com.example.faz3.entity.Suggestion;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Order> RateRequest(List<Order> orders);
    Optional<Customer> Login(String user, String pass);
    void registerOrder(OrderDto orderDto);
    void chanelPassword(Customer customer, String pass);
    void singUp(CustomerDto customerDto);
    List<Suggestion> showSuggestionByPrice(Order order);
    List<Suggestion> showSuggestionByScore(Order order);
    void selectExpert(Customer customer, int orderId, int suggestionId);
    void startWork(Customer customer, int orderId);
    boolean checkStartDate(Customer customer, int orderId);
    Optional<Customer> findByUsername(String username);




}
