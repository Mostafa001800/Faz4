package com.example.faz3.controller;

import com.example.faz3.dto.ListOrderDto;
import com.example.faz3.dto.RequestExpertDto;
import com.example.faz3.dto.SuggestionDto;
import com.example.faz3.dto.expert.ExpertDto;
import com.example.faz3.entity.Expert;
import com.example.faz3.entity.Order;
import com.example.faz3.entity.SubService;
import com.example.faz3.service.ExpertService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/expert")
public class ExpertController {
    private final ExpertService expertService;

    @PostMapping("/singup")
    public void singUp(@RequestBody ExpertDto expertDto) {
        expertService.singUp(expertDto);
    }

    @PostMapping("/request-expert")
    public void requestExpert(@RequestBody RequestExpertDto requestExpertDto) {
        expertService.requestExpert(requestExpertDto);
    }

    @GetMapping("/my-work/{expertUsername}")
    public ListOrderDto works(@PathVariable String expertUsername) {
        Expert expert = expertService.findByUsername(expertUsername).get();
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

}
