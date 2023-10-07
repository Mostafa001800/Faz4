package com.example.homeService.entity;

import com.example.homeService.base.domain.Person;
import com.example.homeService.entity.enu.StatusExpert;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Expert extends Person {
    byte[] image;
    double wallet =0;
    double Score=0;
    StatusExpert statusExpert;
    @ManyToMany(mappedBy = "experts",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    List<SubService> subServices=new ArrayList<>();
    @OneToMany(mappedBy = "expert" ,fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    List<Order> orders=new ArrayList<>();

//    public Expert(String firstName, String lastName, String username, String password
//            , String email) {
//        super(firstName, lastName, username, password,UserRole.EXPERT, email);
//        this.statusExpert = StatusExpert.waiting;
//    }
}
