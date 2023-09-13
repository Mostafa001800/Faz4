package com.example.faz3.mapper;

import com.example.faz3.dto.CustomerDto;
import com.example.faz3.dto.ManagerDto;
import com.example.faz3.entity.Customer;
import com.example.faz3.entity.Manager;
import com.example.faz3.entity.enu.UserRole;

public class ManagerMapper {
    public Manager convert(ManagerDto managerDto){
        Manager manager=new Manager();
        manager.setFirstName(managerDto.getFirstName());
        manager.setLastName(managerDto.getLastName());
        manager.setUsername(managerDto.getUsername());
        manager.setPassword(managerDto.getPassword());
        manager.setEmail(managerDto.getEmail());
        manager.setUserRole(UserRole.MANAGER);
        return manager;
    }
}
