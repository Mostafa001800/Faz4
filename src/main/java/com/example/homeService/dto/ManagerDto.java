package com.example.homeService.dto;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManagerDto {
    String firstName;
    String lastName;
    String username;
    String password;
    String email;
}
