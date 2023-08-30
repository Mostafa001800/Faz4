package com.example.faz3.entity;

import com.example.faz3.base.domain.BaseEntity;
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
    @OneToOne
//    @JoinColumn(name = "Order_id", referencedColumnName = "id")
    Order Order;
    int Score;
    String title;
}
