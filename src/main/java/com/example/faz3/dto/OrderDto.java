package com.example.faz3.dto;

import lombok.*;

import java.time.LocalDateTime;
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    String customerUsername;
    String subServiceTitle;
    String title;
    double suggestedPrice;
    LocalDateTime date;
    String address;
}
