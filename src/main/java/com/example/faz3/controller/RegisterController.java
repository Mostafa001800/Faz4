package com.example.faz3.controller;

import com.example.faz3.dto.CustomerDto;
import com.example.faz3.dto.ManagerDto;
import com.example.faz3.dto.expert.ExpertDto;
import com.example.faz3.service.CustomerService;
import com.example.faz3.service.ExpertService;
import com.example.faz3.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/register")
public class RegisterController {

    private final RegisterService registerService;
    @PostMapping("/singup-manager")
    public void singUpCustomer(@RequestBody ManagerDto managerDto) {
        registerService.newManagerSingUp(managerDto);
    }

    @PostMapping("/singup-expert")
    public void singUpExpert(@RequestBody ExpertDto expertDto) {
        registerService.newExpertSingUp(expertDto);
    }

    @PostMapping("/singup-customer")
    public void singUpCustomer(@RequestBody CustomerDto CustomerDto) {
        registerService.newCustomerSingUp(CustomerDto);
    }

    @GetMapping("/confirm")
    public String confirm(@RequestParam("token") String token) {
        return registerService.confirmToken(token);
    }
}
