package com.rex.boilerplate.springboot.repository;

import com.rex.boilerplate.springboot.model.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
}
