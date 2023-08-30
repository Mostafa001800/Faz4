package com.example.faz3.repository;

import com.example.faz3.entity.RequestExpert;
import com.example.faz3.entity.enu.StatusExpert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestExpertRepository extends JpaRepository<RequestExpert,Long> {
//    @Query("select m from RequestExpert m where m.statusExpert=:statusExpert")
    List<RequestExpert> findByStatusExpert(StatusExpert statusExpert);
//    @Query(value = "select m from RequestExpert m where m.id=?1 ",nativeQuery = true)
//    Optional<RequestExpert> findById(Long Id);
}
