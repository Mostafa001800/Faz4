package com.example.faz3.dto;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    String firstName;
    String lastName;
    String username;
    String password;
    String email;
}
