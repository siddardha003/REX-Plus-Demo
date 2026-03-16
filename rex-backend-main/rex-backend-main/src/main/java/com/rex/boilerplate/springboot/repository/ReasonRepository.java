package com.rex.boilerplate.springboot.repository;

import com.rex.boilerplate.springboot.model.Reason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReasonRepository extends JpaRepository<Reason, Long> {

    List<Reason> findByPlatform_IdAndOccasion_Id(Long platformId, Long occasionId);

}