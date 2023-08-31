package com.example.faz3.mapper;

import com.example.faz3.dto.OrderDto;
import com.example.faz3.entity.Order;

public class OrderMapper {
    public Order convert(OrderDto orderDto){
        Order order=new Order();
        order.setTitle(orderDto.getTitle());
        order.setDate(orderDto.getDate());
        order.setAddress(orderDto.getAddress());
        order.setSuggestedPrice(orderDto.getSuggestedPrice());
        return order;
    }
    public OrderDto convert(Order order){
        OrderDto orderDto=new OrderDto();
        orderDto.setCustomerUsername(order.getCustomer().getUsername());
        orderDto.setSubServiceTitle(order.getSubService().getTitle());
        orderDto.setTitle(order.getTitle());
        orderDto.setDate(order.getDate());
        orderDto.setAddress(order.getAddress());
        orderDto.setSuggestedPrice(order.getSuggestedPrice());
        return orderDto;
    }
}
