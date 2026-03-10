package com.rex.boilerplate.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rex.boilerplate.springboot.model.PicklistToBeneficiary;
import com.rex.boilerplate.springboot.model.PicklistToBeneficiaryId;

@Repository
public interface PicklistToBeneficiaryRepository
        extends JpaRepository<PicklistToBeneficiary, PicklistToBeneficiaryId> {
}
