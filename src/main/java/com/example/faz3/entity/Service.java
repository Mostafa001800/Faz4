package com.example.faz3.entity;

import com.example.faz3.base.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Service extends BaseEntity<Long> {
    @Column(unique = true)
    String title;
    @OneToMany(mappedBy = "service",fetch = FetchType.EAGER)
    Set<SubService> subServices= new HashSet<>();

    @Override
    public String toString() {
        return
                title ;
    }
}
