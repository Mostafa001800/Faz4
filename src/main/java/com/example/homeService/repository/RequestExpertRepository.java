package com.example.homeService.repository;

import com.example.homeService.entity.RequestExpert;
import com.example.homeService.entity.enu.StatusExpert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestExpertRepository extends JpaRepository<RequestExpert,Long> {
//    @Query("select m from RequestExpert m where m.statusExpert=:statusExpert")
    List<RequestExpert> findByStatusExpert(StatusExpert statusExpert);
//    @Query(value = "select m from RequestExpert m where m.id=?1 ",nativeQuery = true)
//    Optional<RequestExpert> findById(Long Id);
}
