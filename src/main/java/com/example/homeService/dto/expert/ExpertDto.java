package com.example.homeService.dto.expert;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpertDto {
    String firstName;
    String lastName;
    String username;
    String password;
    String email;
}
