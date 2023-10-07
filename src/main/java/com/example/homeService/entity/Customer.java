package com.example.homeService.entity;

import com.example.homeService.base.domain.Person;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends Person {
    double wallet =0;
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    List<Order> orders=new ArrayList<>();

//    public Customer(String firstName, String lastName, String username
//            , String password, String email) {
//        super(firstName, lastName, username, password,UserRole.CUSTOMER, email);
//    }
}
