package com.example.faz3.service;

import com.example.faz3.dto.CustomerDto;
import com.example.faz3.dto.ManagerDto;
import com.example.faz3.dto.expert.ExpertDto;

public interface RegisterService {
    void newExpertSingUp(ExpertDto expertDto);

    void newCustomerSingUp(CustomerDto customerDto);
    void newManagerSingUp(ManagerDto managerDto);

    String confirmToken(String token);
}
