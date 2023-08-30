package com.example.faz3.controller;

import com.example.faz3.dto.RequestExpertDto;
import com.example.faz3.dto.expert.ExpertDto;
import com.example.faz3.service.ExpertService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public void requestExpert(@RequestBody RequestExpertDto requestExpertDto){
        expertService.requestExpert(requestExpertDto);
    }

}
