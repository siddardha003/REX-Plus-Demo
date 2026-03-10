package com.rex.boilerplate.springboot.repository;

import com.rex.boilerplate.springboot.model.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformRepository extends JpaRepository<Platform, Long> {
}
