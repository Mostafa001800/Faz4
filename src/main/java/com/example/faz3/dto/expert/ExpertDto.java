package com.example.faz3.dto.expert;

import jakarta.persistence.Column;
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
