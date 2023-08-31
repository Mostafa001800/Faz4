package com.example.faz3.service.impl;

import com.example.faz3.entity.Order;
import com.example.faz3.repository.OrderRepository;
import com.example.faz3.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.repository = orderRepository;
    }
@Transactional
    @Override
    public void save(Order order) {
        repository.save(order);
    }

    @Override
    public Optional<Order> findById(Long Id) {
        Optional<Order> byId = repository.findById(Id);
        return byId;
    }

    @Override
    public List<Order> findAll() {
        List<Order> all = repository.findAll();
        return all;
    }

    @Override
    @Transactional
    public void update(Order order) {
        repository.save(order);
    }
}
