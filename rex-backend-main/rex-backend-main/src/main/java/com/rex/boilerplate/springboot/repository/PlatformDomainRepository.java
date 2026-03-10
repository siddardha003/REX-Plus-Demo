package com.rex.boilerplate.springboot.repository;

import com.rex.boilerplate.springboot.model.PlatformDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformDomainRepository extends JpaRepository<PlatformDomain, Long> {
}
