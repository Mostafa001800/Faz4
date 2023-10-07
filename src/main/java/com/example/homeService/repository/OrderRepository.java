package com.example.homeService.repository;

import com.example.homeService.entity.Customer;
import com.example.homeService.entity.Order;
import com.example.homeService.entity.SubService;
import com.example.homeService.entity.enu.StatusOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCustomerAndStatusOrder(Customer customer, StatusOrder statusOrder);
@Query("select m from Order m where m.date BETWEEN ?1 AND ?2")
    List<Order> orderBetweenDate(LocalDateTime after, LocalDateTime before);

    List<Order> findByStatusOrder(StatusOrder statusOrder);
    List<Order> findBySubService(SubService subService);
}
