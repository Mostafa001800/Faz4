package com.example.faz3.dto.manager;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubServiceDto {
    double basePrice;
    String title;
    Long serviceId;
}
