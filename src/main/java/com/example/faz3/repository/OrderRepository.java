package com.example.faz3.repository;

import com.example.faz3.entity.Customer;
import com.example.faz3.entity.Order;
import com.example.faz3.entity.enu.StatusOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
List<Order> findByCustomerAndStatusOrder(Customer customer, StatusOrder statusOrder);
}
