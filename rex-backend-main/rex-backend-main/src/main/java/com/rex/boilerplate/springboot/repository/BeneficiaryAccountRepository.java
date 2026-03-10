package com.rex.boilerplate.springboot.repository;

import com.rex.boilerplate.springboot.model.BeneficiaryAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiaryAccountRepository extends JpaRepository<BeneficiaryAccount, Long> {
}
