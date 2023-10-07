package com.example.homeService.mapper;

import com.example.homeService.dto.manager.ServiceDto;
import com.example.homeService.entity.Service;

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
