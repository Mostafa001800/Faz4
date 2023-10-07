package com.example.homeService.service.impl;

import com.example.homeService.dto.manager.ServiceDto;
import com.example.homeService.exception.InvalidPasswordException;
import com.example.homeService.exception.NotFoundException;
import com.example.homeService.exception.SaveException;
import com.example.homeService.mapper.ServiceMapper;
import com.example.homeService.repository.ServiceRepository;
import com.example.homeService.service.ServiceService;
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
    public boolean repetitive(com.example.homeService.entity.Service service) {
        List<com.example.homeService.entity.Service> findAll = repository.findAll();
        boolean b = false;
        for (com.example.homeService.entity.Service s : findAll) {
            if (s.getTitle().equals(service.getTitle())) {
                b = true;
            }
        }
        return b;
    }
    @Transactional
    @Override
    public void save(ServiceDto serviceDto) throws InvalidPasswordException {
        com.example.homeService.entity.Service service=serviceMapper.convert(serviceDto);
        if (repetitive(service) == false) {
            repository.save(service);
        } else{
            throw new SaveException("There was a problem registering");
        }
    }
    @Transactional
    @Override
    public void update(com.example.homeService.entity.Service service){
        repository.save(service);
    }
    @Transactional
    @Override
    public void delete(ServiceDto serviceDto){
        com.example.homeService.entity.Service service = serviceMapper.convert(serviceDto);
        Optional<com.example.homeService.entity.Service> findByTitle = findByTitle(service.getTitle());
        if(findByTitle==null){
            throw new NotFoundException("Not found Service");
        }
        repository.deleteById(findByTitle.get().getId());
    }
    @Override
    public Optional<com.example.homeService.entity.Service> findById(Long Id){
        Optional<com.example.homeService.entity.Service> byId = repository.findById(Id);
        return byId;
    }
    @Override
    public Optional<com.example.homeService.entity.Service> findByTitle(String s){
        Optional<com.example.homeService.entity.Service> byTitle = repository.findByTitle(s);
        return byTitle;
    }
}
