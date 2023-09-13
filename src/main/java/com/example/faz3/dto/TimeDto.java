package com.example.faz3.dto;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeDto {
    LocalDateTime after;
    LocalDateTime before;
}
