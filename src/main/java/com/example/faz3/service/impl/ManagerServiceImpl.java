package com.example.faz3.service.impl;

import com.example.faz3.Validation.Validation;
import com.example.faz3.dto.manager.ServiceDto;
import com.example.faz3.dto.manager.SubServiceDto;
import com.example.faz3.entity.Expert;
import com.example.faz3.entity.Manager;
import com.example.faz3.entity.RequestExpert;
import com.example.faz3.entity.SubService;
import com.example.faz3.entity.enu.StatusExpert;
import com.example.faz3.exception.*;
import com.example.faz3.repository.ManagerRepository;
import com.example.faz3.service.ManagerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ManagerServiceImpl implements ManagerService {
    private final ManagerRepository repository;
    private final SubServiceServiceImpl subServiceServiceImpl;
    private final ServiceServiceImpl serviceServiceImpl;
    private final ExpertServiceImpl expertServiceImpl;

    private final RequestExpertServiceImpl requestExpertServiceImpl;

    @Override
    @Transactional
    public void addExpert(String titleSubService, String expertUsername) {
        SubService subService = subServiceServiceImpl.findByTitle(titleSubService).get();
        Expert expert = expertServiceImpl.findByUsername(expertUsername).get();
        subService.getExperts().add(expert);
        subServiceServiceImpl.update(subService);
//        expertServiceImpl.update(expert);
    }

    @Override
    @Transactional
    public void removeExpert(String titleSubService, String expertUsername) {
        SubService subService = subServiceServiceImpl.findByTitle(titleSubService).get();
        Expert expert = expertServiceImpl.findByUsername(expertUsername).get();
//        expert.getSubServices().remove(subService);
        subService.getExperts().remove(expert);
        subServiceServiceImpl.update(subService);
    }

    @Override
    public Optional<Manager> login(String user, String pass) {

        Optional<Manager> manager;

        manager = repository.findByUsername(user);
        if (manager.isPresent()) {
            if (manager.get().getPassword().equals(pass)) {
                return manager;
            } else {
                throw new WrongException("The username or password is incorrect");
            }
        } else {
            throw new NotFoundException("not found Manager ");
        }
    }

    @Override
    @Transactional
    public void addService(ServiceDto serviceDto) {

        serviceServiceImpl.save(serviceDto);
    }

    @Override
    @Transactional
    public void removeService(ServiceDto serviceDto) {
        serviceServiceImpl.delete(serviceDto);
    }

    @Override
    @Transactional
    public void changePassword(Manager manager, String pass) {
        if (Validation.isValidPassword(pass) == true) {
            manager.setPassword(pass);
            repository.save(manager);
        } else {
            throw new InvalidPasswordException("The password format is not correct !");
        }
    }

    @Override
    @Transactional
    public void addSubService(SubServiceDto subServiceDto) {
        subServiceServiceImpl.save(subServiceDto);
    }

    @Override
    @Transactional
    public void removeSubService(Long id) {
        subServiceServiceImpl.deleteById(id);
    }

    @Override
    @Transactional
    public void changeBasePrice(Long id, double newPrice) {
        SubService byId = subServiceServiceImpl.findById(id).get();
        if (byId != null) {
            byId.setBasePrice(newPrice);
            subServiceServiceImpl.update(byId);
        } else {
            throw new NotFoundException("not found SubService ");
        }
    }

    @Override
    public List<RequestExpert> showRequestExperts() {
        List<RequestExpert> list = requestExpertServiceImpl.findStatusWaiting();
            return list;
    }

    @Override
    @Transactional
    public void changeStatusRequestExpert(Long requestExpertId, Long statusExpert) {
        RequestExpert requestExpert = requestExpertServiceImpl.findById(requestExpertId).get();
        if (requestExpert != null) {
            if(statusExpert==1){
                requestExpert.setStatusExpert(StatusExpert.accept);
            } else if (statusExpert==0) {
                requestExpert.setStatusExpert(StatusExpert.Refuse);
            }else {
                throw new InputeException("The input value is greater than number 1");
            }
            requestExpertServiceImpl.save(requestExpert);
        } else {
            throw new NotFoundException("Not found Request");
        }
    }

    @Override
    @Transactional
    public void changeTitleSubService(Long id, String newTitle) {
        SubService byId = subServiceServiceImpl.findById(id).get();
        if (byId != null) {
            byId.setTitle(newTitle);
            subServiceServiceImpl.update(byId);
        } else {
            throw new NotFoundException("not found SubService ");
        }
    }

    @Override
    @Transactional
    public void changeTitleService(Long id, String newTitle) {
        com.example.faz3.entity.Service service = serviceServiceImpl.findById(id).get();
        if (service != null) {
            service.setTitle(newTitle);
            serviceServiceImpl.update(service);
        } else {
            throw new NotFoundException("not found SubService ");
        }
    }
}
