package com.example.faz3.service;

import com.example.faz3.dto.*;
import com.example.faz3.dto.manager.ServiceDto;
import com.example.faz3.dto.manager.SubServiceDto;
import com.example.faz3.entity.Manager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ManagerService {
    void singup(ManagerDto managerDto);

    void addExpert(String titleSubService, String expertUsername);

    void removeExpert(String titleSubService, String expertUsername);

    void changeTitleService(Long id, String newTitle);

    void changeTitleSubService(Long id, String newTitle);

    void changeStatusRequestExpert(Long requestExpertId, Long statusExpert);

    ListRequestExpertDto showRequestExperts();

    void changeBasePrice(Long id, double newPrice);

    void removeSubService(Long id);

    void addSubService(SubServiceDto subServiceDto);

    void changePassword(Manager manager, String pass);

    void removeService(ServiceDto serviceDto);

    void addService(ServiceDto ServiceDto);

    Optional<Manager> login(String user, String pass);

    ListFilterDto filter(FilterDto filterDto);

    Optional<Manager> findByEmail(String email);

    ListCustomerDto filterOrderCustomer();
    ListExpertDto filterOrderExpert();
}
