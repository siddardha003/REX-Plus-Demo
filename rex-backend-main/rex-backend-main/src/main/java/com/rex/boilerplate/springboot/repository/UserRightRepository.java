package com.rex.boilerplate.springboot.repository;

import com.rex.boilerplate.springboot.model.UserRight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRightRepository extends JpaRepository<UserRight, Long> {
}
