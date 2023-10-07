package com.example.homeService.dto;

import com.example.homeService.dto.expert.ExpertDto;
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
