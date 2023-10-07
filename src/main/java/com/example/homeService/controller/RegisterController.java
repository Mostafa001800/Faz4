package com.example.homeService.controller;

import com.example.homeService.dto.CustomerDto;
import com.example.homeService.dto.ManagerDto;
import com.example.homeService.dto.expert.ExpertDto;
import com.example.homeService.service.RegisterService;
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
