package com.example.faz3.service.impl;

import com.example.faz3.Validation.Validation;
import com.example.faz3.dto.CustomerDto;
import com.example.faz3.dto.OrderDto;
import com.example.faz3.entity.Customer;
import com.example.faz3.entity.Expert;
import com.example.faz3.entity.Order;
import com.example.faz3.entity.Suggestion;
import com.example.faz3.entity.enu.StatusOrder;
import com.example.faz3.exception.InputeException;
import com.example.faz3.exception.InvalidPasswordException;
import com.example.faz3.exception.NotFoundException;
import com.example.faz3.exception.WrongException;
import com.example.faz3.mapper.CustomerMapper;
import com.example.faz3.mapper.OrderMapper;
import com.example.faz3.repository.CustomerRepository;
import com.example.faz3.service.CustomerService;
import com.example.faz3.service.SubServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repository;
    private final OrderServiceImpl orderServiceImpl;
    private final ExpertServiceImpl expertServiceImpl;
    private final SubServiceService subServiceService;
    CustomerMapper customerMapper = new CustomerMapper();
    OrderMapper orderMapper=new OrderMapper();

    @Override
    public Optional<Customer> Login(String user, String pass) {
        boolean B = false;
        Optional<Customer> customer;
        try {
            customer = repository.findByUsername(user);
            if (customer.isPresent()) {
                if (customer.get().getPassword().equals(pass)) {
                    B = true;
                } else {
                    throw new WrongException("The username or password is incorrect");
                }
            }
        } catch (Exception e) {
            throw new NotFoundException("not found Customer ");
        }
        if (B == false) {
            return null;
        } else
            return customer;
    }

    @Override
    public List<Order> RateRequest(List<Order> orders) {
        List<Order> list = new ArrayList<>();
        for (Order order : orders) {
            if (order.getStatusOrder() == StatusOrder.Done)
                list.add(order);
        }
        return list;
    }

    @Override
    public void chanelPassword(Customer customer, String pass) {
        if (Validation.isValidPassword(pass) == true) {
            customer.setPassword(pass);
            repository.save(customer);
        } else {
            throw new InvalidPasswordException("The password format is not correct !");
        }
    }

    @Override
    public void singUp(CustomerDto customerDto) {
        Customer customer = customerMapper.convert(customerDto);
        if ((Validation.isValidEmail(customer.getEmail())) == false && Validation.isValidPassword(customer.getPassword()) == false) {
            throw new InvalidPasswordException("The email or password format is not correct !");
        } else {
            repository.save(customer);
        }
    }

    @Override
    public List<Suggestion> showSuggestionByPrice(Long orderId) {
        Order order = orderServiceImpl.findById(orderId).get();
        List<Suggestion> suggestions = order.getSuggestion();
        Collections.sort(suggestions,Comparator.comparing(Suggestion::getPrice));
        return suggestions;
    }

    @Override
    public List<Suggestion> showSuggestionByScore(Order order) {
        List<Suggestion> suggestions = order.getSuggestion();
        Collections.sort(suggestions, Comparator.comparingDouble(s -> s.getExpert().getScore()));
        return suggestions;
    }

    @Override
    public void selectExpert(Customer customer, int orderId, int suggestionId) {
        if (customer.getOrders().size() > orderId) {
            if (customer.getOrders().get(orderId).getSuggestion().size() > suggestionId) {
                Order order = orderServiceImpl.findById(customer.getOrders().get(orderId).getId()).get();
                Expert expert = expertServiceImpl.findById(customer.getOrders().get(orderId).getSuggestion().get(suggestionId).getId()).get();
                order.setExpert(expert);
                order.setStatusOrder(StatusOrder.ComingTowardsYou);
                orderServiceImpl.save(order);
            } else {
                throw new NotFoundException("You entered the wrong number suggestionId");
            }
        } else {
            throw new NotFoundException("You entered the wrong number orderId");
        }
    }

    @Override
    public void startWork(Customer customer, int orderId) {
        if (customer.getOrders().size() > orderId) {
            if (customer.getOrders().get(orderId).getStatusOrder() == StatusOrder.ComingTowardsYou) {
                if (checkStartDate(customer, orderId) == false) {
                    Order order = orderServiceImpl.findById(customer.getOrders().get(orderId).getId()).get();
                    order.setStatusOrder(StatusOrder.Started);
                    orderServiceImpl.save(order);
                } else {
                    throw new WrongException("The time should not be earlier than the suggested time");
                }
            } else {
                throw new InputeException("The work you need is not in the right condition");
            }
        } else {
            throw new NotFoundException("You entered the wrong number orderId");
        }

    }

    @Override
    public boolean checkStartDate(Customer customer, int orderId) {
        boolean test = false;
        List<Suggestion> list = customer.getOrders().get(orderId).getSuggestion();
        Suggestion suggestion = new Suggestion();
        for (Suggestion s : list) {
            if (s.getExpert() == customer.getOrders().get(orderId).getExpert()) {
                suggestion = s;
            }
        }
        if (suggestion.getDate().isAfter(LocalDateTime.now())) {
            test = true;
        }
        return test;
    }

    @Override
    public void registerOrder(OrderDto orderDto) {
        Order order = orderMapper.convert(orderDto);
        order.setCustomer(findByUsername(orderDto.getCustomerUsername()).get());
        order.setSubService(subServiceService.findByTitle("PlasterWork").get());
        orderServiceImpl.save(order);
    }

    @Override
    public Optional<Customer> findByUsername(String username) {
        Optional<Customer> byUsername = repository.findByUsername(username);
        return byUsername;
    }
}
