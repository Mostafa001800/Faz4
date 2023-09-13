package com.example.faz3.entity;

import com.example.faz3.base.domain.Person;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@NoArgsConstructor
@Entity
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Manager extends Person {

}
