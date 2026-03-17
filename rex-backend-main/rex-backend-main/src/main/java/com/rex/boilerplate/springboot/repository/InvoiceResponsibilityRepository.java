package com.rex.boilerplate.springboot.repository;

import com.rex.boilerplate.springboot.model.InvoiceResponsibility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;
import java.time.LocalDate;

@Repository
public interface InvoiceResponsibilityRepository
        extends JpaRepository<InvoiceResponsibility, Long> {

    Optional<InvoiceResponsibility>
    findByInvoiceIdAndRexuserIdAndIsActiveTrue(
            Long invoiceId,
            Long rexuserId
    );

    @Query("""
SELECT 
    i.id,
    i.vendor,
    i.documentName,
    i.documentDate,
    i.documentNumber,
    i.invoiceTotalGross,
    i.currency,
    irActive.delegateId
FROM InvoiceResponsibility irOld
JOIN InvoiceResponsibility irActive 
     ON irOld.invoiceId = irActive.invoiceId
JOIN Invoices i 
     ON i.id = irOld.invoiceId
WHERE irOld.rexuserId = :rexUserId
AND irOld.isActive = false
AND irActive.isActive = true
AND i.documentDate BETWEEN :from AND :to
""")
    Page<Object[]> findDelegatedInvoices(
            Long rexUserId,
            LocalDate from,
            LocalDate to,
            Pageable pageable
    );
}