package com.rex.boilerplate.springboot.repository;

import com.rex.boilerplate.springboot.model.DeputyRight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeputyRightRepository extends JpaRepository<DeputyRight, Long> {
}
