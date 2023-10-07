package com.example.homeService.controller;

import com.example.homeService.base.domain.Person;
import com.example.homeService.dto.*;
import com.example.homeService.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/register-order")
    public void registerOrder(@RequestBody OrderDto orderDto) {
        customerService.registerOrder(orderDto);
    }

    @GetMapping("/show-SuggestionByPrice/{orderId}")
    public ListSuggestionDto showSuggestionByPrice(@PathVariable Long orderId) {
        ListSuggestionDto suggestionDto = customerService.showSuggestionByPrice(orderId);
        return suggestionDto;
    }

    @GetMapping("/show-SuggestionByScore/{orderId}")
    public ListSuggestionDto showSuggestionByScore(@PathVariable Long orderId) {
        ListSuggestionDto suggestionsDto = customerService.showSuggestionByScore(orderId);
        return suggestionsDto;
    }

    @PutMapping("/select-Expert/{orderId}/{suggestionId}")
    public void selectExpert(@PathVariable Long orderId, @PathVariable Long suggestionId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Person person = (Person) authentication.getPrincipal();

        customerService.selectExpert(person.getUsername(), orderId, suggestionId);
    }

    @PutMapping("/startJob/{OrderId}")
    public void startJob(@PathVariable Long OrderId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Person person = (Person) authentication.getPrincipal();

        customerService.startJob(person.getUsername(), OrderId);
    }

    @PutMapping("/endJob/{OrderId}")
    public void endJob(@PathVariable Long OrderId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Person person = (Person) authentication.getPrincipal();

        customerService.endJob(person.getUsername(), OrderId);
    }

    @PutMapping("/cash-Payment")
    ResponseEntity CashPayment(@RequestBody InputJobDto inputJobDto) {
        String s = customerService.CashPayment(inputJobDto);
        return ResponseEntity.ok().body(s);
    }

    //    @PutMapping("/card-Payment")
//    ModelAndView CardPayment(@PathVariable Model model ){
//        InputJobDto inputJobDto=new InputJobDto();
//        model.addAttribute("timer",inputJobDto);
//
//        String s = customerService.CashPayment(inputJobDto);
//        return new ModelAndView("index");
//    }
    @GetMapping("/card-Payment/{orderId}")
    ModelAndView CardPayment(@PathVariable Long orderId, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Person person = (Person) authentication.getPrincipal();

        InputJobDto inputJobDto = new InputJobDto(person.getUsername(), orderId);
//        model.addAttribute("timer", inputJobDto);
//        String s = customerService.CashPayment(inputJobDto);
        return new ModelAndView("index");
    }

    @PostMapping("/lea")
    public ResponseEntity checkData(Model model) {

        return null;
    }

    @PostMapping("/registerComment")
    public void registerComment(@RequestBody CommentDto commentDto) {
        customerService.registerComment(commentDto);
    }

    @PostMapping("/show-wallet")
    public double showWallet() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        Person person=(Person) authentication.getPrincipal();
        return customerService.showWallet(person.getUsername());

    }
    @GetMapping("/show-order-statusOrder/{status}")
    public ListOrderDto showOrderStatusOrder(@PathVariable String status) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        Person person=(Person) authentication.getPrincipal();
        return customerService.showOrderStatusOrder(person.getUsername(),status);
    }
}
