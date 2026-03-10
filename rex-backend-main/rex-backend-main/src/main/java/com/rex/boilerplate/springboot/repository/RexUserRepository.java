package com.rex.boilerplate.springboot.repository;

import com.rex.boilerplate.springboot.model.RexUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RexUserRepository extends JpaRepository<RexUser, Long> {
}
