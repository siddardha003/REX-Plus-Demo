package com.rex.boilerplate.springboot.repository;

import com.rex.boilerplate.springboot.model.Events;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Events, Long> {

    List<Events> findByPlatform_Id(Long platformId);

}