package com.example.homeService.service;

import com.example.homeService.dto.CustomerDto;
import com.example.homeService.dto.ManagerDto;
import com.example.homeService.dto.expert.ExpertDto;

public interface RegisterService {
    void newExpertSingUp(ExpertDto expertDto);

    void newCustomerSingUp(CustomerDto customerDto);
    void newManagerSingUp(ManagerDto managerDto);

    String confirmToken(String token);
}
