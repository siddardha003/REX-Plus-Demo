package com.rex.boilerplate.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rex.boilerplate.springboot.model.DeputyRightAssignment;
import com.rex.boilerplate.springboot.model.DeputyRightAssignmentId;

@Repository
public interface DeputyRightAssignmentRepository
        extends JpaRepository<DeputyRightAssignment, DeputyRightAssignmentId> {
}
