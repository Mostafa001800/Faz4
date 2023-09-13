package com.example.faz3.service.impl;

import com.example.faz3.dto.CustomerDto;
import com.example.faz3.dto.ManagerDto;
import com.example.faz3.dto.expert.ExpertDto;
import com.example.faz3.entity.Expert;
import com.example.faz3.exception.SaveException;
import com.example.faz3.security.tokan.ConfigurationToken;
import com.example.faz3.security.tokan.ConfigurationTokenService;
import com.example.faz3.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    private final PersonService personService;
    private final ManagerService managerService;
    private final ExpertService expertService;
    private final CustomerService customerService;
    private final ConfigurationTokenService configurationTokenService;

    @Override
    public void newManagerSingUp(ManagerDto managerDto) {
        managerService.singup(managerDto);
    }

    @Override
    public void newExpertSingUp(ExpertDto expertDto) {
        Optional<Expert> expert = expertService.findByEmail(expertDto.getEmail());
        if (expert.isPresent()){
            throw new SaveException("This email is already registered");
        }
        expertService.newSingUp(expertDto);

    }

    @Override
    public void newCustomerSingUp(CustomerDto customerDto) {
        customerService.newSingUp(customerDto);
    }


    @Override
    public String confirmToken(String token) {
        Optional<ConfigurationToken> confirmToken = configurationTokenService.getToken(token);

        if (confirmToken.isEmpty()) {
            throw new IllegalStateException("Token not found!");
        }

        if (confirmToken.get().getConfirmedAt() != null) {
            throw new IllegalStateException("Email is already confirmed");
        }

        LocalDateTime expiresAt = confirmToken.get().getExpiresAt();

        if (expiresAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Token is already expired!");
        }

        personService.enablePerson(confirmToken.get().getPerson().getEmail());
        configurationTokenService.setConfirmedAt(token);

        //Returning confirmation message if the token matches
        return "Your email is confirmed. Thank you for using our service!";
    }
    }
//}
