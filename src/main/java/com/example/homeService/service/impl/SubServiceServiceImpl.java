package com.example.homeService.service.impl;

import com.example.homeService.dto.manager.SubServiceDto;
import com.example.homeService.entity.SubService;
import com.example.homeService.exception.NotFoundException;
import com.example.homeService.exception.SaveException;
import com.example.homeService.mapper.SubServiceMapper;
import com.example.homeService.repository.SubServiceRepository;
import com.example.homeService.service.SubServiceService;
import jakarta.transaction.Transactional;
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
@Transactional
    @Override
    public void update(SubService subService) {
        repository.save(subService);
    }
    @Transactional
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
    @Transactional
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
