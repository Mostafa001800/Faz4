package com.example.faz3.dto;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    String customerUsername;
    Long orderId;
    int score;
    String title;
}
