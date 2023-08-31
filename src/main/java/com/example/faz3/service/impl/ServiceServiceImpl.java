package com.example.faz3.service.impl;

import com.example.faz3.dto.manager.ServiceDto;
import com.example.faz3.exception.InvalidPasswordException;
import com.example.faz3.exception.NotFoundException;
import com.example.faz3.exception.SaveException;
import com.example.faz3.mapper.ServiceMapper;
import com.example.faz3.repository.ServiceRepository;
import com.example.faz3.service.ServiceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Service
public class ServiceServiceImpl implements ServiceService {
    private final ServiceRepository repository;
    private final ServiceMapper serviceMapper=new ServiceMapper();
//    @Autowired
//    public ServiceServiceImpl(ServiceRepository serviceRepository) {
//        this.repository = serviceRepository;
//    }
    @Override
    public boolean repetitive(com.example.faz3.entity.Service service) {
        List<com.example.faz3.entity.Service> findAll = repository.findAll();
        boolean b = false;
        for (com.example.faz3.entity.Service s : findAll) {
            if (s.getTitle().equals(service.getTitle())) {
                b = true;
            }
        }
        return b;
    }
    @Transactional
    @Override
    public void save(ServiceDto serviceDto) throws InvalidPasswordException {
        com.example.faz3.entity.Service service=serviceMapper.convert(serviceDto);
        if (repetitive(service) == false) {
            repository.save(service);
        } else{
            throw new SaveException("There was a problem registering");
        }
    }
    @Transactional
    @Override
    public void update(com.example.faz3.entity.Service service){
        repository.save(service);
    }
    @Transactional
    @Override
    public void delete(ServiceDto serviceDto){
        com.example.faz3.entity.Service service = serviceMapper.convert(serviceDto);
        Optional<com.example.faz3.entity.Service> findByTitle = findByTitle(service.getTitle());
        if(findByTitle==null){
            throw new NotFoundException("Not found Service");
        }
        repository.deleteById(findByTitle.get().getId());
    }
    @Override
    public Optional<com.example.faz3.entity.Service> findById(Long Id){
        Optional<com.example.faz3.entity.Service> byId = repository.findById(Id);
        return byId;
    }
    @Override
    public Optional<com.example.faz3.entity.Service> findByTitle(String s){
        Optional<com.example.faz3.entity.Service> byTitle = repository.findByTitle(s);
        return byTitle;
    }
}
