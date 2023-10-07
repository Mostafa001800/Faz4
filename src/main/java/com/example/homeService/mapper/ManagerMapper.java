package com.example.homeService.mapper;

import com.example.homeService.dto.ManagerDto;
import com.example.homeService.entity.Manager;
import com.example.homeService.entity.enu.UserRole;

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
