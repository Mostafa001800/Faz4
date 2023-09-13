package com.example.faz3.service.impl;

import com.example.faz3.entity.Customer;
import com.example.faz3.entity.Order;
import com.example.faz3.entity.SubService;
import com.example.faz3.entity.enu.StatusOrder;
import com.example.faz3.repository.OrderRepository;
import com.example.faz3.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Override
    public List<Order> findByCustomerAndStatusOrder(Customer customer, StatusOrder statusOrder) {
        List<Order> orders = repository.findByCustomerAndStatusOrder(customer, statusOrder);
        return orders;
    }

    @Override
    public List<Order> OrderBetweenDate(LocalDateTime after, LocalDateTime before) {
       return repository.orderBetweenDate(after,before);
    }

    @Override
    public List<Order> findByStatusOrder(StatusOrder statusOrder) {
        return repository.findByStatusOrder(statusOrder);
    }

    @Override
    public List<Order> findBySubService(SubService subService) {
        return repository.findBySubService(subService);
    }
}
