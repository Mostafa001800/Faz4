package com.example.faz3.entity;

import com.example.faz3.base.domain.BaseEntity;
import com.example.faz3.entity.enu.StatusOrder;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "orders")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order extends BaseEntity<Long> {
    @ManyToOne
    Customer customer;
    @ManyToOne
    SubService subService;
    String title;
    double SuggestedPrice;
    @OneToOne
    Comment comment;
    LocalDateTime date;
    String Address;
    @ManyToOne
    Expert expert;
    @Enumerated(EnumType.STRING)
    StatusOrder statusOrder=StatusOrder.ExpertSuggestions;
    @OneToMany(mappedBy = "order",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    List<Suggestion> Suggestion=new ArrayList<>();

    @Override
    public String toString() {
        return "Order{" +
                "customer=" + customer.getId() +
                ", subService=" + subService.getId() +
                ", title='" + title + '\'' +
                ", SuggestedPrice=" + SuggestedPrice +
//                ", comment=" + comment.getId() +
                ", date=" + date +
                ", Address='" + Address + '\'' +
                ", statusOrder=" + statusOrder +
                ", Suggestion=" + Suggestion.size() +
                '}';
    }
}
