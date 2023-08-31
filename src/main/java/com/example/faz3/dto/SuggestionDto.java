package com.example.faz3.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SuggestionDto {
    String expertUsername;
    Long orderId;
    LocalDateTime date;
    double price;
    LocalTime durationOfWork;
}
