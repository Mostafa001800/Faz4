package com.example.faz3.service.impl;

import com.example.faz3.dto.manager.SubServiceDto;
import com.example.faz3.entity.SubService;
import com.example.faz3.exception.NotFoundException;
import com.example.faz3.exception.SaveException;
import com.example.faz3.mapper.SubServiceMapper;
import com.example.faz3.repository.SubServiceRepository;
import com.example.faz3.service.SubServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SubServiceServiceImpl implements SubServiceService {
    private final SubServiceRepository repository;
    private final ServiceServiceImpl serviceServiceImpl;
    SubServiceMapper subServiceMapper = new SubServiceMapper();
//@Autowired
//    public SubServiceServiceImpl(SubServiceRepository repository) {
//        this.repository = repository;
//    }

    @Override
    public void update(SubService subService) {
        repository.save(subService);
    }

    @Override
    public void save(SubServiceDto subServiceDto) {
        SubService subService = subServiceMapper.convert(subServiceDto);
        subService.setService(serviceServiceImpl.findById(subServiceDto.getServiceId()).get());
        if (repetitive(subService) == false) {
            if (subService.getService() != null) {
                repository.save(subService);
            } else {
                throw new NotFoundException("Not found Service");
            }
        } else {
            throw new SaveException("There was a problem registering");
        }
    }

    @Override
    public void deleteById(Long id) {
//        SubService subService = findById(id).get();
        repository.deleteById(id);
    }

    @Override
    public boolean repetitive(SubService subService) {
        List<SubService> all = repository.findAll();
        boolean b = false;
        for (SubService s : all) {
            if (s.getTitle().equals(subService.getTitle())) {
                b = true;
            }
        }
        return b;
    }

    @Override
    public Optional<SubService> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<SubService> findByTitle(String title) {
        Optional<SubService> byTitle = repository.findByTitle(title);
        return byTitle;
    }
}
