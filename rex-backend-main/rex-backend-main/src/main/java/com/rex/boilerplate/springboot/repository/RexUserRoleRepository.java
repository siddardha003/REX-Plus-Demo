package com.rex.boilerplate.springboot.repository;

import com.rex.boilerplate.springboot.model.RexUserRole;
import com.rex.boilerplate.springboot.model.RexUserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RexUserRoleRepository extends JpaRepository<RexUserRole, RexUserRoleId> {
}
