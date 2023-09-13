package com.example.faz3.service.impl;

import com.example.faz3.repository.PersonRepository;
import com.example.faz3.service.PersonService;
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
