package com.example.homeService.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestExpertDto {
    String expertUsername;
    String subServiceTitle;
}
