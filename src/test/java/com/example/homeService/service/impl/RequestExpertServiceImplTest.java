package com.example.homeService.service.impl;

import com.example.homeService.service.RequestExpertService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import static org.junit.jupiter.api.Assertions.*;
@Component
@SpringBootTest
class RequestExpertServiceImplTest {
    @Autowired
RequestExpertService requestExpertService;
    @Test
    void findStatusWaiting() {
        int i=requestExpertService.findStatusWaiting().size();
        assertEquals(0,i);
    }
}