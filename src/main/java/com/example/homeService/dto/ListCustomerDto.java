package com.example.homeService.dto;

import lombok.*;

import java.util.List;
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListCustomerDto {
    List<CustomerDto> list;

}
