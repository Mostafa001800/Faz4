package com.example.faz3.controller;

import com.example.faz3.dto.CustomerDto;
import com.example.faz3.dto.OrderDto;
import com.example.faz3.entity.Customer;
import com.example.faz3.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapping;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;
    @PostMapping("/singup")
    public void singUp(@RequestBody CustomerDto CustomerDto){
        customerService.singUp(CustomerDto);
    }
    @PostMapping("/register-order")
    public void registerOrder(@RequestBody OrderDto orderDto){
        customerService.registerOrder(orderDto);
    }

}
