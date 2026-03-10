package com.rex.boilerplate.springboot.repository;

import com.rex.boilerplate.springboot.model.BeneficiaryOrgData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiaryOrgDataRepository extends JpaRepository<BeneficiaryOrgData, Long> {
}
