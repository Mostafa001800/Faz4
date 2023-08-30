package com.example.faz3.mapper;

import com.example.faz3.dto.manager.ServiceDto;
import com.example.faz3.entity.Service;

public class ServiceMapper {

    public Service convert(ServiceDto serviceDto){
        Service service=new Service();
        service.setTitle(serviceDto.getTitle());
        return service;
    }
//    public Service convert(Service service){
//        ServiceDto serviceDto=new ServiceDto();
//        serviceDto.setTitle();
//    }
}
