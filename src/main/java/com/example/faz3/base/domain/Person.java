package com.example.faz3.base.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@NoArgsConstructor
@MappedSuperclass
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Person extends BaseEntity<Long>{
    String firstName;
    String lastName;
    @Column(unique = true)
    String username;
    String password;

}
