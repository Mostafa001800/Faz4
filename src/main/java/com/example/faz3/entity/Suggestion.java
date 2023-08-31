package com.example.faz3.entity;

import com.example.faz3.base.domain.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Suggestion extends BaseEntity<Long> {
    @ManyToOne
    Expert expert;
    @ManyToOne
    Order order;
    LocalDateTime date;
    double price;
    LocalTime DurationOfWork;
    boolean accepted;
    @Override
    public String toString() {
        return "Suggestion{" +
                "expert=" + expert.getUsername() +
                ", order=" + order.getId() +
                ", date=" + date +
                ", price=" + price +
                ", DurationOfWork=" + DurationOfWork +
                '}'+"\n";
    }

    public void setAccepted(boolean accepted) {
        this.accepted = true;
    }
}
