package com.rex.boilerplate.springboot.repository;

import com.rex.boilerplate.springboot.model.Invoices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoices, Long> {
}