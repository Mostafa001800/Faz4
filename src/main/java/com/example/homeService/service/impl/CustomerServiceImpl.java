package com.example.homeService.service.impl;

import com.example.homeService.Validation.Validation;
import com.example.homeService.dto.*;
import com.example.homeService.entity.*;
import com.example.homeService.entity.enu.StatusOrder;
import com.example.homeService.entity.enu.UserRole;
import com.example.homeService.exception.*;
import com.example.homeService.mapper.CommentMapper;
import com.example.homeService.mapper.CustomerMapper;
import com.example.homeService.mapper.OrderMapper;
import com.example.homeService.mapper.SuggestionMapper;
import com.example.homeService.repository.CustomerRepository;
import com.example.homeService.security.tokan.ConfigurationToken;
import com.example.homeService.security.tokan.ConfigurationTokenService;
import com.example.homeService.service.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
    private final PasswordEncoder passwordEncoder;
    private final CustomerRepository repository;
    private final OrderServiceImpl orderServiceImpl;
    private final ExpertServiceImpl expertServiceImpl;
    private final SubServiceService subServiceService;
    private final SuggestionService suggestionService;
    private final CommentServiceImpl commentServiceImpl;
    private final EmailService emailService;
    CustomerMapper customerMapper = new CustomerMapper();
    OrderMapper orderMapper = new OrderMapper();
    CommentMapper commentMapper = new CommentMapper();
    SuggestionMapper suggestionMapper = new SuggestionMapper();
    private final ConfigurationTokenService configurationTokenService;

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

    @Transactional
    @Override
    public void singUp(CustomerDto customerDto) {
        Customer customer = customerMapper.convert(customerDto);
        customer.setUserRole(UserRole.CUSTOMER);
        if ((Validation.isValidEmail(customer.getEmail())) == false && Validation.isValidPassword(customer.getPassword()) == false) {
            throw new InvalidPasswordException("The email or password format is not correct !");
        } else {
            repository.save(customer);
        }
    }

    @Override
    public ListSuggestionDto showSuggestionByPrice(Long orderId) {
        List<SuggestionDto> list = new ArrayList<>();
        Order order = orderServiceImpl.findById(orderId).get();
        List<Suggestion> suggestions = order.getSuggestion();
        Collections.sort(suggestions, Comparator.comparing(Suggestion::getPrice));
        for (Suggestion s : suggestions) {
            list.add(suggestionMapper.convert(s));
        }
        return new ListSuggestionDto(list);
    }

    @Override
    public ListSuggestionDto showSuggestionByScore(Long orderId) {
        List<SuggestionDto> list = new ArrayList<>();
        Order order = orderServiceImpl.findById(orderId).get();
        List<Suggestion> suggestions = order.getSuggestion();
        Collections.sort(suggestions, Comparator.comparingDouble(Suggestion::getPrice).reversed());
        for (Suggestion s : suggestions) {
            list.add(suggestionMapper.convert(s));
        }
        return new ListSuggestionDto(list);
    }

    @Transactional
    @Override
    public void selectExpert(String customerUsername, Long orderId, Long suggestionId) {
        Customer customer = findByUsername(customerUsername).get();
//        if (customer.getOrders().size() > orderId) {
//            if (customer.getOrders().get(orderId).getSuggestion().size() > suggestionId) {
//                Order order = orderServiceImpl.findById(customer.getOrders().get(orderId).getId()).get();
//                Expert expert = expertServiceImpl.findById(customer.getOrders().get(orderId).getSuggestion().get(suggestionId).getId()).get();
//                order.setExpert(expert);
//                order.setStatusOrder(StatusOrder.ComingTowardsYou);
//                orderServiceImpl.save(order);
//            } else {
//                throw new NotFoundException("You entered the wrong number suggestionId");
//            }
//        } else {
//            throw new NotFoundException("You entered the wrong number orderId");
//        }


//        if(checkOrder(customer,orderId,suggestionId)==true){
//            Order order = orderServiceImpl.findById(customer.getOrders().get(orderId).getId()).get();
//            Expert expert = expertServiceImpl.findById(customer.getOrders().get(orderId).getSuggestion().get(suggestionId).getId()).get();
//            order.setExpert(expert);
//            order.setStatusOrder(StatusOrder.ComingTowardsYou);
//            orderServiceImpl.save(order);
//        } else {
//            throw new NotFoundException("You entered the wrong number orderId or suggestionId");
//        }

        List<Order> orders = customer.getOrders();
        for (Order order : orders) {
            if (order.getId().equals(orderId) && order.getStatusOrder() == StatusOrder.ExpertSelection) {
                List<Suggestion> listSuggestion = order.getSuggestion();
                System.out.println("ok");
                for (Suggestion s : listSuggestion) {
                    System.out.println("-ok");
                    if (s.getId().equals(suggestionId)) {
                        System.out.println("--ok");
                        order.setExpert(s.getExpert());
                        order.setStatusOrder(StatusOrder.ComingTowardsYou);
                        orderServiceImpl.update(order);
                        s.setAccepted(true);
                        suggestionService.save(s);
                    }
                }
            }
        }


    }

    @Transactional
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

    @Transactional
    @Override
    public void registerOrder(OrderDto orderDto) {
        Order order = orderMapper.convert(orderDto);
        order.setCustomer(findByUsername(orderDto.getCustomerUsername()).get());
        order.setSubService(subServiceService.findByTitle(orderDto.getSubServiceTitle()).get());
        if (order.getDate().isAfter(LocalDateTime.now())) {
            orderServiceImpl.save(order);
        }
    }

    @Override
    public Optional<Customer> findByUsername(String username) {
        Optional<Customer> byUsername = repository.findByUsername(username);
        return byUsername;
    }

    @Transactional
    @Override
    public void startJob(String customerUsername, Long OrderId) {
        Customer customer = findByUsername(customerUsername).get();
        List<Order> orders = customer.getOrders();
        for (Order a : orders) {
            if (a.getId().equals(OrderId) && a.getStatusOrder() == StatusOrder.ComingTowardsYou) {
                a.setStatusOrder(StatusOrder.Started);
                a.setDate(LocalDateTime.now());
                orderServiceImpl.update(a);
            }
        }
    }

    @Transactional
    @Override
    public void endJob(String customerUsername, Long OrderId) {
        Customer customer = findByUsername(customerUsername).get();
        List<Order> orders = customer.getOrders();
        boolean test;
        for (Order a : orders) {
            if (a.getId().equals(OrderId) && a.getStatusOrder() == StatusOrder.Started) {
                a.setStatusOrder(StatusOrder.Done);
                orderServiceImpl.update(a);

                Duration duration = Duration.between(a.getDate(), LocalDateTime.now());
                long days = duration.toDays();
                long hours = duration.toHours() % 24;
                long minutes = duration.toMinutes() % 60;
                long delay = (minutes + (hours * 60) + (days * 1440)) / 30;

                Expert expert = a.getExpert();        //update Score
                expert.setScore(expert.getScore() - (delay * 0.1));
                if (expert.getScore() <= 0) {
                    expert.getSubServices().clear();
                }
                expertServiceImpl.update(expert);
                System.out.println(expert.getSubServices());
            }
        }
    }

    @Transactional
    @Override
    public String CashPayment(InputJobDto inputJobDto) {
        Optional<Customer> customer = findByUsername(inputJobDto.getCustomerUsername());
        if (customer.isEmpty()) {
            throw new NotFoundException("* not found Customer *");
        }
        Optional<Order> order = orderServiceImpl.findById(inputJobDto.getOrderId());
        if (order.isEmpty() && order.get().getCustomer() != customer.get()) {
            throw new NotFoundException("* not found Order *");
        }
        if (order.get().getStatusOrder() != StatusOrder.Done) {
            throw new WrongException("* The order is not in proper condition *");
        }

        Suggestion suggestion = new Suggestion();
        List<Suggestion> listSuggestion = order.get().getSuggestion();
        for (Suggestion s : listSuggestion) {
            if (s.isAccepted() == true) {
                suggestion = s;
            }
        }
        if (customer.get().getWallet() <= suggestion.getPrice()) {
            throw new PaymentException("*Inventory is low *");
        }
        Expert expert = suggestion.getExpert();

        customer.get().setWallet((customer.get().getWallet()) - (suggestion.getPrice()));
        order.get().setStatusOrder(StatusOrder.Payment);
        expert.setWallet(expert.getWallet() + ((suggestion.getPrice() / 100) * 70));
        expertServiceImpl.update(expert);
        orderServiceImpl.update(order.get());
        repository.save(customer.get());
        return "Success";

//        List<Order> orders = customer.getOrders();
//        Suggestion suggestion = new Suggestion();
//        for (Order a : orders) {
//            if (a.getId().equals(OrderId) && a.getStatusOrder() == StatusOrder.Done) {
//                for (Suggestion s : a.getSuggestion()) {
//                    if (s.isAccept() == true) {
//                        suggestion = s;
//                    }
//                }
//            }
//        }
//        if (customer.getWallet() >= suggestion.getPrice()) {
//            customer.setWallet(customer.getWallet() - suggestion.getPrice());
//            a.setStatusOrder(StatusOrder.Done);
//            orderServiceImpl.update(a);
//        }

    }

    @Transactional
    @Override
    public void registerComment(CommentDto commentDto) {
        Comment comment = commentMapper.convert(commentDto);
        Optional<Customer> customer = findByUsername(commentDto.getCustomerUsername());
        if (customer.isEmpty()) {
            throw new NotFoundException("* not found Customer *");
        }
        Optional<Order> order = orderServiceImpl.findById(commentDto.getOrderId());
        if (order.isEmpty() && order.get().getCustomer() != customer.get()) {
            throw new NotFoundException("* not found Order *");
        }
        if (order.get().getStatusOrder() != StatusOrder.Payment) {
            throw new WrongException("* The order is not in proper condition *");
        }
        comment.setOrder(order.get());
        commentServiceImpl.save(comment);
        expertServiceImpl.updateScore(comment);


        order.get().setComment(comment);
        orderServiceImpl.update(order.get());
    }

    @Override
    public List<Customer> finAll() {
        return repository.findAll();
    }

    @Override
    public void newSingUp(CustomerDto customerDto) {
        Customer customer = customerMapper.convert(customerDto);
        customer.setUserRole(UserRole.CUSTOMER);
        if ((Validation.isValidEmail(customer.getEmail())) == false && Validation.isValidPassword(customer.getPassword()) == false) {
            throw new InvalidPasswordException("The email or password format is not correct !");
        } else {
            customer.setPassword(passwordEncoder.encode(customerDto.getPassword()));
            repository.save(customer);
        }

        String newToken = UUID.randomUUID().toString();
        ConfigurationToken configurationToken = new ConfigurationToken(LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), customer);
        configurationToken.setToken(newToken);

        configurationTokenService.saveConfigurationToken(configurationToken);
        SimpleMailMessage mailMessage =
                emailService.createEmail(customer.getEmail(), configurationToken.getToken(), customer.getUserRole());
        emailService.sendEmail(mailMessage);
    }

    @Override
    public double showWallet(String customerUsername) {
        Optional<Customer> customer = findByUsername(customerUsername);
        return customer.get().getWallet();
    }

    @Override
    public ListOrderDto showOrderStatusOrder(String customerUsername, String status) {
        Customer customer = findByUsername(customerUsername).get();

        StatusOrder statusOrder = null;
        if (status.equals("ExpertSelection")) {
            statusOrder = StatusOrder.ExpertSelection;
        } else if (status.equals("ExpertSuggestions")) {
            statusOrder = StatusOrder.ExpertSuggestions;
        } else if (status.equals("ComingTowardsYou")) {
            statusOrder = StatusOrder.ComingTowardsYou;
        } else if (status.equals("Started")) {
            statusOrder = StatusOrder.Started;
        } else if (status.equals("Done")) {
            statusOrder = StatusOrder.Done;
        } else if (status.equals("Payment")) {
            statusOrder = StatusOrder.Payment;
        } else {
            throw new InputeException("You entered the status name incorrectly!");
        }

        List<OrderDto> orderDtoList = new ArrayList<>();
        List<Order> orderList = orderServiceImpl.findByCustomerAndStatusOrder(customer, statusOrder);
        for (Order order : orderList) {
            OrderDto convert = orderMapper.convert(order);
            orderDtoList.add(convert);
        }
        return new ListOrderDto(orderDtoList);
    }
}

