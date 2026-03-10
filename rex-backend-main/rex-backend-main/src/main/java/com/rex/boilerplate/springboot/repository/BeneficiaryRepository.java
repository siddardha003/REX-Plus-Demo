package com.rex.boilerplate.springboot.repository;

import com.rex.boilerplate.springboot.model.Beneficiary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long>, JpaSpecificationExecutor<Beneficiary> {
    java.util.Optional<Beneficiary> findByBeneficiaryId(java.util.UUID beneficiaryId);

    @Override
    @NonNull
    Page<Beneficiary> findAll(@Nullable Specification<Beneficiary> specification, @NonNull Pageable pageable);
}
