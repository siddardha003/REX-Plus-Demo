package com.rex.boilerplate.springboot.repository;

import com.rex.boilerplate.springboot.model.Occasions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OccasionRepository extends JpaRepository<Occasions, Long> {

    List<Occasions> findByPlatform_Id(Long platformId);

}