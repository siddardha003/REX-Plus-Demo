package com.rex.boilerplate.springboot.repository;

import com.rex.boilerplate.springboot.model.ProjectLeader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectLeaderRepository
        extends JpaRepository<ProjectLeader, Long>, JpaSpecificationExecutor<ProjectLeader> {
    List<ProjectLeader> findByProjectId(Long projectId);

    List<ProjectLeader> findByRexUserId(Long rexUserId);
}
