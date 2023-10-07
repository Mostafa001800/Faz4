package com.example.homeService.repository;

import com.example.homeService.base.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person,Long> {

    @Transactional
    @Modifying
    @Query("UPDATE Person p SET p.isEnabled=true WHERE p.email=?1")
    void enablePerson(String email);

    Optional<Person> findByUsername(String username);
}
