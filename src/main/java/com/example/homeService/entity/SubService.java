package com.example.homeService.entity;

import com.example.homeService.base.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubService extends BaseEntity<Long> {
    @ManyToOne
    Service service;
    @OneToMany(mappedBy = "subService",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    List<Order> orders=new ArrayList<>();
    double basePrice;
    String title;
    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    List<Expert> experts=new ArrayList<>();
    @Override
    public String toString() {
        return
                title + '\'';
    }
}
