package com.example.faz3.controller;

import com.example.faz3.dto.ListRequestExpertDto;
import com.example.faz3.dto.addExpertToSubService;
import com.example.faz3.dto.manager.ServiceDto;
import com.example.faz3.dto.manager.SubServiceDto;
import com.example.faz3.entity.Expert;
import com.example.faz3.entity.RequestExpert;
import com.example.faz3.entity.SubService;
import com.example.faz3.service.ExpertService;
import com.example.faz3.service.ManagerService;
import com.example.faz3.service.SubServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/manager")
public class ManagerController {
    private final ManagerService managerService;
    private final SubServiceService subServiceService;
    private final ExpertService expertService;

    @PostMapping("/add-service")
    public void addService(@RequestBody ServiceDto serviceDto) {
        managerService.addService(serviceDto);
    }

    @DeleteMapping("/delete-service")
    public void removeService(@RequestBody ServiceDto serviceDto) {
        managerService.removeService(serviceDto);
    }

    @PutMapping("/change-title-service/{id}/{newTitle}")
    public void changeTitleService(@PathVariable Long id, @PathVariable String newTitle) {
        managerService.changeTitleService(id, newTitle);
    }

    @PostMapping("/add-subservice")
    public void addSubService(@RequestBody SubServiceDto subServiceDto) {
        managerService.addSubService(subServiceDto);
    }

    @DeleteMapping("/delete-subservice/{id}")
    public void removeSubService(@PathVariable Long id) {
        managerService.removeSubService(id);
    }

    @PutMapping("/change-baseprice-subservice/{id}/{newPrice}")
    public void changeBasePrice(@PathVariable Long id, @PathVariable double newPrice) {
        managerService.changeBasePrice(id, newPrice);
    }

    @PutMapping("/change-title-subservice/{id}/{newTitle}")
    List<String> changeTitleSubService(@PathVariable Long id, @PathVariable String newTitle) {//
        managerService.changeTitleSubService(id, newTitle);
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        return list;
    }

    @GetMapping ("/show-Request-Experts")
    public ListRequestExpertDto showRequestExperts() {
        return managerService.showRequestExperts();
    }

    @PutMapping("/change-status-RequestExpert/{requestExpertId}/{statusExpert}")
    void changeStatusRequestExpert(@PathVariable Long requestExpertId, @PathVariable Long statusExpert) {
        managerService.changeStatusRequestExpert(requestExpertId, statusExpert);
    }
    @PostMapping("/add-expertToSubService")
    int addExpert(@RequestBody addExpertToSubService dto){
        managerService.addExpert(dto.getTitleSubService(), dto.getExpertUsername());
        SubService subService = subServiceService.findByTitle("PlasterWork").get();
        System.out.println(subService.getExperts());
//        subService.getExperts().clear();
        Expert expert = expertService.findByUsername("M").get();
//        expert.getSubServices().clear();
        List<Expert> experts = subService.getExperts();
        return experts.size();
    }
    @DeleteMapping("/remove-Expert")
    List<Expert> removeExpert(@RequestBody addExpertToSubService dto){
        managerService.removeExpert(dto.getTitleSubService(), dto.getExpertUsername());
        SubService subService = subServiceService.findByTitle(dto.getTitleSubService()).get();
        return subService.getExperts();
    }


}
