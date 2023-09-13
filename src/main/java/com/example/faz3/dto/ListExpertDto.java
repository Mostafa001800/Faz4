package com.example.faz3.dto;

import com.example.faz3.dto.expert.ExpertDto;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListExpertDto {
    List<ExpertDto> list;
}
