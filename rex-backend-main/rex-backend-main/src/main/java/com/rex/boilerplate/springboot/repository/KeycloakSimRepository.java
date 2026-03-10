package com.rex.boilerplate.springboot.repository;

import com.rex.boilerplate.springboot.model.KeycloakSim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface KeycloakSimRepository extends JpaRepository<KeycloakSim, UUID> {
}
