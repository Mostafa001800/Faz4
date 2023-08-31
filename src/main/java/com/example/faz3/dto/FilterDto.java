package com.example.faz3.dto;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilterDto {
    FilterEnum filterEnum;
    String firstName;
    String lastName;
    String username;
    String password;
    String email;
    String subServiceTitle;
    double score;
}
