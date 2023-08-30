package com.example.faz3.service;

import com.example.faz3.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    public void save(Order order);
    public void update(Order order);
    public Optional<Order> findById(Long Id);
    public List<Order> findAll();
}
