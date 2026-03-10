package com.rex.boilerplate.springboot.repository;

import com.rex.boilerplate.springboot.model.UserRoleRight;
import com.rex.boilerplate.springboot.model.UserRoleRightId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRightRepository extends JpaRepository<UserRoleRight, UserRoleRightId> {
}
