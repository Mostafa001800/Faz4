package com.example.faz3.dto;

import com.example.faz3.entity.Expert;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpertCountDto extends Expert {
    int count;
}
