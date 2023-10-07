package com.example.homeService.service.impl;

import com.example.homeService.repository.PersonRepository;
import com.example.homeService.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {
    private final PersonRepository repository;
    @Override
    public void enablePerson(String email) {
        repository.enablePerson(email);
    }

}
