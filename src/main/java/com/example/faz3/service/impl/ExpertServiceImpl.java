package com.example.faz3.service.impl;

import com.example.faz3.Validation.Validation;
import com.example.faz3.dto.RequestExpertDto;
import com.example.faz3.dto.SuggestionDto;
import com.example.faz3.dto.expert.ExpertDto;
import com.example.faz3.entity.*;
import com.example.faz3.entity.enu.StatusOrder;
import com.example.faz3.exception.*;
import com.example.faz3.mapper.ExpertMapper;
import com.example.faz3.mapper.SuggestionMapper;
import com.example.faz3.repository.ExpertRepository;
import com.example.faz3.service.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ExpertServiceImpl implements ExpertService {
    private final ExpertRepository repository;
    private final SuggestionServiceImpl suggestionServiceImpl;
    private final SubServiceServiceImpl subServiceServiceImpl;
    private final OrderServiceImpl orderServiceImpl;
    private final RequestExpertServiceImpl requestExpertServiceImpl;
    private final SuggestionMapper suggestionMapper;
    ExpertMapper expertMapper = new ExpertMapper();


//    @Autowired
//    public ExpertServiceImpl(ExpertRepository repository, SuggestionServiceImpl suggestionServiceImpl, SubServiceServiceImpl subServiceServiceImpl, OrderServiceImpl orderServiceImpl, RequestExpertServiceImpl requestExpertServiceImpl) {
//        this.repository = repository;
//        this.suggestionServiceImpl = suggestionServiceImpl;
//        this.subServiceServiceImpl = subServiceServiceImpl;
//        this.orderServiceImpl = orderServiceImpl;
//        this.requestExpertServiceImpl = requestExpertServiceImpl;
//    }

    @Override
    public Optional<Expert> login(String user, String pass) {
        boolean B = false;
        Optional<Expert> expert;
        try {
            expert = repository.findByUsername(user);
            if (expert.isPresent()) {
                if (expert.get().getPassword().equals(pass)) {
                    B = true;
                } else throw new WrongException("The username or password is incorrect");
            }
        } catch (Exception e) {
            throw new NotFoundException("not found Expert ");
        }
        if (B == false) {
            return null;
        } else
            return expert;
    }

    @Override
    public List<Order> works(Expert expert) {
        List<Order> orders = new ArrayList<>();
        List<SubService> subServices = expert.getSubServices();
        List<Order> All = orderServiceImpl.findAll();
        for (SubService subService : subServices) {
            for (Order order : All) {
                if (order.getSubService() == subService
                        && (order.getStatusOrder() == StatusOrder.ExpertSuggestions
                              ||  order.getStatusOrder() == StatusOrder.ExpertSelection)
                                ) {
                    orders.add(order);
                }
            }
        }
        return orders;
    }

    @Override
    @Transactional
    public void chanelPassword(Expert expert, String pass) {
        if (Validation.isValidPassword(pass)) {
//            Expert expert1=findById(expert.getId()).get();
            expert.setPassword(pass);
            repository.save(expert);
        } else {
            throw new InvalidPasswordException("The password format is not correct !");
        }
    }

    @Override
    @Transactional
    public void singUp(ExpertDto expertDto) {
        Expert expert = expertMapper.convert(expertDto);
//        if ((Validation.isValidEmail(expert.getEmail())) == false && Validation.isValidPassword(expert.getPassword()) == false) {
//            throw new InvalidPasswordException("The email or Password format is not correct !");
//        } else {
        repository.save(expert);
//        }
    }

    @Override
    @Transactional
    public void saveImage(Expert expert, String location) {
        File file = new File(location);
        byte[] byteImg = new byte[(int) file.length()];
        String[] path = file.getPath().split("\\.");
        if (!path[path.length - 1].equalsIgnoreCase("JPG"))
            throw new FormatException(".............");
        try {
            if (Files.size(Paths.get(file.getPath())) > 300000)
                throw new SizeException("................");
        } catch (IOException e) {
            e.printStackTrace();
        }
        expert.setImage(byteImg);
        repository.save(expert);
    }

    @Override
    @Transactional
    public void registerTheOffer(SuggestionDto suggestionDto) {
        Suggestion suggestion = suggestionMapper.convert(suggestionDto);
        suggestion.setExpert(findByUsername(suggestionDto.getExpertUsername()).get());
        suggestion.setOrder(orderServiceImpl.findById(suggestionDto.getOrderId()).get());
//        Suggestion suggestion = suggestionMapper.convert(suggestionDto);
//        if (suggestion.getOrder().getStatusOrder() == StatusOrder.ExpertSuggestions) {
//            Order order = suggestion.getOrder();
//            order.setStatusOrder(StatusOrder.ExpertSelection);
//            orderServiceImpl.save(order);
//        }
        suggestionServiceImpl.save(suggestion);
    }


    @Override
    @Transactional
    public void requestExpert(RequestExpertDto requestExpertDto) {
        RequestExpert requestExpert = new RequestExpert();
        requestExpert.setExpert(findByUsername(requestExpertDto.getExpertUsername()).get());
        requestExpert.setSubService(subServiceServiceImpl.findByTitle(requestExpertDto.getSubServiceTitle()).get());

        requestExpertServiceImpl.save(requestExpert);
    }

    @Override
    public Optional<Expert> findById(Long id) {
        Optional<Expert> byId = repository.findById(id);
        return byId;
    }

    @Override
    public Optional<Expert> findByUsername(String user) {
        Optional<Expert> byUsername = repository.findByUsername(user);
        return byUsername;
    }

    @Override
    public boolean isExpertList(String subService, String expertUser) {
        SubService subService1 = subServiceServiceImpl.findById(2L).get();
        Expert expert = findByUsername(expertUser).get();
        boolean test = false;
        for (SubService s : expert.getSubServices()) {
            if (s.getTitle().equals(subService1.getTitle())) {
                test = true;
                break;
            }
        }
        return test;
    }

    @Override
    public void update(Expert expert) {
        repository.save(expert);
    }
}