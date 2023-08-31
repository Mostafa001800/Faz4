package com.example.faz3.service;

import com.example.faz3.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    void save(Order order);
    Optional<Order> findById(Long Id);
    List<Order> findAll();

    void update(Order order);
}
