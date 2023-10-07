package com.example.homeService.service;

import com.example.homeService.dto.*;
import com.example.homeService.entity.Customer;
import com.example.homeService.entity.Order;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Order> RateRequest(List<Order> orders);

    Optional<Customer> Login(String user, String pass);

    void registerOrder(OrderDto orderDto);

    void chanelPassword(Customer customer, String pass);

    void singUp(CustomerDto customerDto);

    ListSuggestionDto showSuggestionByPrice(Long orderId);

    ListSuggestionDto showSuggestionByScore(Long orderId);

    void selectExpert(String customerUsername, Long orderId, Long suggestionId);

    void startWork(Customer customer, int orderId);

    boolean checkStartDate(Customer customer, int orderId);

    Optional<Customer> findByUsername(String username);

    void startJob(String customerUsername, Long OrderId);

    void endJob(String customerUsername, Long OrderId);

    String CashPayment(InputJobDto inputJobDto);

    void registerComment(CommentDto commentDto);

    List<Customer> finAll();

    void newSingUp(CustomerDto customerDto);
    double showWallet(String customerUsername);
    ListOrderDto showOrderStatusOrder(String customerUsername, String status);
}
