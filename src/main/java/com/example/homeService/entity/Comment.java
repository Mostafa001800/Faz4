package com.example.homeService.entity;

import com.example.homeService.base.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Setter
@Getter
@NoArgsConstructor
@Entity
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Comment extends BaseEntity<Long> {
    @OneToOne(mappedBy = "comment")
//    @JoinColumn(name = "Order_id", referencedColumnName = "id")
    Order order;
    int score;
    String title;
}
