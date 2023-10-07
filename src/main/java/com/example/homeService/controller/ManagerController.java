package com.example.homeService.controller;

import com.example.homeService.dto.*;
import com.example.homeService.dto.manager.ServiceDto;
import com.example.homeService.dto.manager.SubServiceDto;
import com.example.homeService.service.ExpertService;
import com.example.homeService.service.ManagerService;
import com.example.homeService.service.SubServiceService;
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

    @GetMapping("/show-Request-Experts")
    public ListRequestExpertDto showRequestExperts() {
        return managerService.showRequestExperts();
    }

    @PutMapping("/change-status-RequestExpert/{requestExpertId}/{statusExpert}")
    public void changeStatusRequestExpert(@PathVariable Long requestExpertId, @PathVariable Long statusExpert) {
        managerService.changeStatusRequestExpert(requestExpertId, statusExpert);
    }

    @PostMapping("/add-expertToSubService")
    public void addExpert(@RequestBody addExpertToSubService dto) {
        managerService.addExpert(dto.getTitleSubService(), dto.getExpertUsername());
//        SubService subService = subServiceService.findByTitle(dto.getTitleSubService()).get();
//        System.out.println(subService.getExperts());
//        subService.getExperts().clear();
//        Expert expert = expertService.findByUsername("M").get();
//        expert.getSubServices().clear();
//        List<Expert> experts = subService.getExperts();
//        return experts.size();
    }

    @DeleteMapping("/remove-Expert")
    public void removeExpert(@RequestBody addExpertToSubService dto) {
        managerService.removeExpert(dto.getTitleSubService(), dto.getExpertUsername());
//        SubService subService = subServiceService.findByTitle(dto.getTitleSubService()).get();
//        return subService.getExperts();
    }

    @GetMapping("/filter")
    public ListFilterDto filterDto(@RequestBody FilterDto filterDto) {
        return managerService.filter(filterDto);
    }

    @GetMapping("/test")
    public FilterDto test(@RequestBody FilterDto filterDto) {
        return filterDto;
    }

    @GetMapping("/showRequestCustomer")
    public ListCustomerDto filterOrderDoneCustomer() {
        return managerService.filterOrderCustomer();
    }

    @GetMapping("/showFilterOrderExpert")
    public ListExpertDto filterOrderExpert() {
        return null;
    }

    @GetMapping("/showOrderBetweenDateSto")
    public ListOrderDto showOrderBetweenDateSto(@RequestBody TimeDto sto) {
        return managerService.showOrderBetweenDate(sto.getAfter(), sto.getBefore());
    }

    @GetMapping("/showOrdersByStatusOrder/{status}")
    public ListOrderDto showOrdersByStatusOrder(@PathVariable String status) {
        return managerService.showOrdersByStatusOrder(status);
    }

    @GetMapping("showOrderBySubService/{subServiceTitle}")
    public ListOrderDto showOrderBySubService(@PathVariable String subServiceTitle) {
        return managerService.showOrderBySubService(subServiceTitle);
    }

    @GetMapping("showCustomerByConfirmedAt")
    public ListCustomerDto showCustomerByConfirmedAt(@RequestBody TimeDto timeDto) {
        return managerService.showCustomerByConfirmedAt(timeDto.getAfter(), timeDto.getBefore());
    }
}
