package com.rex.boilerplate.springboot.repository;

import com.rex.boilerplate.springboot.model.InvoiceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceStatusRepository
        extends JpaRepository<InvoiceStatus, Long> {
        Optional<InvoiceStatus> findTopByInvoiceIdOrderByCreatedAtDesc(Long invoiceId);
}