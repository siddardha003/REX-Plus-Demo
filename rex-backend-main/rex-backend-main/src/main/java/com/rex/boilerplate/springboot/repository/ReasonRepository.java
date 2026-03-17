package com.rex.boilerplate.springboot.repository;

import com.rex.boilerplate.springboot.model.Reasons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReasonRepository extends JpaRepository<Reasons, Long> {

    List<Reasons> findByPlatform_IdAndOccasion_Id(Long platformId, Long occasionId);

}