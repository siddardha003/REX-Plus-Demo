package com.rex.boilerplate.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rex.boilerplate.springboot.model.PicklistToGuest;
import com.rex.boilerplate.springboot.model.PicklistToGuestId;

import java.util.List;

@Repository
public interface PicklistToGuestRepository
        extends JpaRepository<PicklistToGuest, PicklistToGuestId> {

    List<PicklistToGuest> findByPicklistId(Long picklistId);

    List<PicklistToGuest> findByGuestId(Long guestId);
}
