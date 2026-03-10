package com.rex.boilerplate.springboot.repository;

import com.rex.boilerplate.springboot.model.Picklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PicklistRepository extends JpaRepository<Picklist, Long> {
}

