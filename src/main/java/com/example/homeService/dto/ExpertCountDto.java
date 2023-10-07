package com.example.homeService.dto;

import com.example.homeService.entity.Expert;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpertCountDto extends Expert {
    int count;
}
