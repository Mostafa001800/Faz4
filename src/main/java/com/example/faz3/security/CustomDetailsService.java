package com.example.faz3.security;

import com.example.faz3.base.domain.Person;
import com.example.faz3.exception.NotFoundException;
import com.example.faz3.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomDetailsService implements UserDetailsService {
    private final PersonRepository personRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = personRepository.findByUsername(username);
        if(person.isEmpty()){
            throw  new NotFoundException("not found Person");
        }
        return person.get();
    }
}
