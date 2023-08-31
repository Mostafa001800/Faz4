package com.example.faz3.entity;

import com.example.faz3.base.domain.Person;
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
public class Expert extends Person {
    String email;
    byte[] image;
    double valet=0;
    double Score=0;
    @ManyToMany(mappedBy = "experts",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    List<SubService> subServices=new ArrayList<>();
    @OneToMany(mappedBy = "expert" ,fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    List<Order> orders=new ArrayList<>();

    @Override
    public String toString() {
        return "username" + getUsername()+
                ", name :" + getFirstName()+" "+getLastName()+
                ", email='" + email + '\'' +
                ", valet=" + valet +
                ", Score=" + Score +
                "\n";
    }
}
