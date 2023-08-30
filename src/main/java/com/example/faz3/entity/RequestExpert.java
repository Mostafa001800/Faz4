package com.example.faz3.entity;

import com.example.faz3.base.domain.BaseEntity;
import com.example.faz3.entity.enu.StatusExpert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestExpert extends BaseEntity<Long> {
    @ManyToOne
    Expert expert;
    @ManyToOne
    SubService subService;
    @Enumerated(EnumType.STRING)
    StatusExpert statusExpert =StatusExpert.waiting;

    @Override
    public String toString() {
        return "RequestExpert{" +
                "expert=" + expert+
                ", subService=" + subService.getTitle() +
                '}';
    }
}
