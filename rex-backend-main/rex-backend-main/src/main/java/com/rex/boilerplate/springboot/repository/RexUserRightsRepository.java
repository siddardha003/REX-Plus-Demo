package com.rex.boilerplate.springboot.repository;

import com.rex.boilerplate.springboot.model.RexUserRights;
import com.rex.boilerplate.springboot.model.RexUserRightsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RexUserRightsRepository extends JpaRepository<RexUserRights, RexUserRightsId> {
}
