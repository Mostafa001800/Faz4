package com.example.faz3.service;

import com.example.faz3.dto.ListOrderDto;
import com.example.faz3.entity.Customer;
import com.example.faz3.entity.Order;
import com.example.faz3.entity.SubService;
import com.example.faz3.entity.enu.StatusOrder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    void save(Order order);
    Optional<Order> findById(Long Id);
    List<Order> findAll();

    void update(Order order);
    List<Order> findByCustomerAndStatusOrder(Customer customer, StatusOrder statusOrder);
    List<Order> OrderBetweenDate(LocalDateTime after, LocalDateTime before);
    List<Order> findByStatusOrder(StatusOrder statusOrder);
    List<Order> findBySubService(SubService subService);

}
