package com.example.faz3.controller;

import com.example.faz3.base.domain.Person;
import com.example.faz3.dto.ListOrderDto;
import com.example.faz3.dto.RequestExpertDto;
import com.example.faz3.dto.SuggestionDto;
import com.example.faz3.dto.expert.ExpertDto;
import com.example.faz3.entity.Expert;
import com.example.faz3.entity.Order;
import com.example.faz3.entity.SubService;
import com.example.faz3.service.ExpertService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/expert")
public class ExpertController {
    private final ExpertService expertService;

    @PostMapping("/request-expert/{subServiceName}")
    public void requestExpert(@PathVariable String subServiceName) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        Person person=(Person) authentication.getPrincipal();
        RequestExpertDto expertDto=new RequestExpertDto(person.getUsername(),subServiceName);
        expertService.requestExpert(expertDto);
    }

    @GetMapping("/my-work")
    public ListOrderDto works() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        Person person=(Person) authentication.getPrincipal();

        Expert expert = expertService.findByUsername(person.getUsername()).get();
        ListOrderDto works = expertService.works(expert);
        return works;
    }

    @PostMapping("/registertheoffer")
    public void RegisterTheOffer(@RequestBody SuggestionDto suggestionDto) {
        expertService.registerTheOffer(suggestionDto);
    }


    @PostMapping("/test")
    public List<SubService> test() {
        Expert expert = expertService.findByUsername("M").get();
        System.out.println(expert.getSubServices());
        return expert.getSubServices();
    }

    @GetMapping("/showScore")
    public double showScore() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        Person person=(Person) authentication.getPrincipal();

        return expertService.showScore(person.getUsername());
    }
}
