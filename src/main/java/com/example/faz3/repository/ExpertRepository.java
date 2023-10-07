package com.example.faz3.repository;

import com.example.faz3.dto.ExpertCountDto;
import com.example.faz3.entity.Expert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Repository
public interface ExpertRepository extends JpaSpecificationExecutor<Expert>,JpaRepository<Expert,Long> {
    Optional<Expert> findByUsername(String user);
    Optional<Expert> findByEmail(String email);
//    @Query("SELECT e,(select count(o.id) from orders o where o.status_order='Done'and o.expert_id=e.id) as order_count FROM expert e order by order_count")
//    Object findExpertByCountWorks();

}
