package com.example.homeService.repository;

import com.example.homeService.entity.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ExpertRepository extends JpaSpecificationExecutor<Expert>,JpaRepository<Expert,Long> {
    Optional<Expert> findByUsername(String user);
    Optional<Expert> findByEmail(String email);
//    @Query("SELECT e,(select count(o.id) from orders o where o.status_order='Done'and o.expert_id=e.id) as order_count FROM expert e order by order_count")
//    Object findExpertByCountWorks();

}
