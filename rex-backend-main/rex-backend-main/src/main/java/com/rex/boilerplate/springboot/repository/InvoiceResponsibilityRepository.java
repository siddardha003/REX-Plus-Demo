package com.rex.boilerplate.springboot.repository;

import com.rex.boilerplate.springboot.model.InvoiceResponsibility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceResponsibilityRepository
        extends JpaRepository<InvoiceResponsibility, Long> {

    Optional<InvoiceResponsibility>
    findByInvoiceIdAndRexuserIdAndIsActiveTrue(
            Long invoiceId,
            Long rexuserId
    );

    List<InvoiceResponsibility>
    findByRexuserIdAndIsActiveFalse(
            Long rexuserId
    );

    Optional<InvoiceResponsibility>
    findByInvoiceIdAndIsActiveTrueAndDelegateIdIsNotNull(
            Long invoiceId
    );

}