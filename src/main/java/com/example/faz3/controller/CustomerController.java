package com.example.faz3.controller;

import com.example.faz3.dto.CommentDto;
import com.example.faz3.dto.CustomerDto;
import com.example.faz3.dto.InputJobDto;
import com.example.faz3.dto.OrderDto;
import com.example.faz3.entity.Customer;
import com.example.faz3.entity.Expert;
import com.example.faz3.entity.Order;
import com.example.faz3.entity.Suggestion;
import com.example.faz3.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapping;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/singup")
    public void singUp(@RequestBody CustomerDto CustomerDto) {
        customerService.singUp(CustomerDto);
    }

    @PostMapping("/register-order")
    public void registerOrder(@RequestBody OrderDto orderDto) {
        customerService.registerOrder(orderDto);
    }

    @GetMapping("/show-SuggestionByPrice/{orderId}")
    public List<Suggestion> showSuggestionByPrice(@PathVariable Long orderId) {
        List<Suggestion> suggestions = customerService.showSuggestionByPrice(orderId);
        System.out.println(suggestions);
        return null;
    }

    @GetMapping("/show-SuggestionByScore/{orderId}")
    public List<Suggestion> showSuggestionByScore(@PathVariable Long orderId) {
        List<Suggestion> suggestions = customerService.showSuggestionByScore(orderId);
        System.out.println(suggestions);
        return null;
    }

    @PutMapping("/select-Expert/{customerUsername}/{orderId}/{suggestionId}")
    public void selectExpert(@PathVariable String customerUsername, @PathVariable Long orderId, @PathVariable Long suggestionId) {
        customerService.selectExpert(customerUsername, orderId, suggestionId);
    }

    @PutMapping("/startJob/{customerUsername}/{OrderId}")
    public void startJob(@PathVariable String customerUsername, @PathVariable Long OrderId) {
        customerService.startJob(customerUsername, OrderId);
    }

    @PutMapping("/endJob/{customerUsername}/{OrderId}")
    public void endJob(@PathVariable String customerUsername, @PathVariable Long OrderId) {
        customerService.endJob(customerUsername, OrderId);
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
    @PutMapping("/card-Payment/{customerUsername}/{orderId}")
    ModelAndView CardPayment(@PathVariable String customerUsername, @PathVariable Long orderId, Model model) {
        InputJobDto inputJobDto = new InputJobDto(customerUsername, orderId);
        model.addAttribute("timer", inputJobDto);
        String s = customerService.CashPayment(inputJobDto);
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


}
