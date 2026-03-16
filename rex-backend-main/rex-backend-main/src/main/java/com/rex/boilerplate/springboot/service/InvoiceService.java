package com.rex.boilerplate.springboot.service;

import com.rex.boilerplate.springboot.dto.DelegatedInvoiceResponseDTO;

import java.time.LocalDate;

public interface InvoiceService {

    void updateInvoiceStatus(Long invoiceId, String status);

    DelegatedInvoiceResponseDTO getDelegatedInvoices(
            Boolean isActive,
            LocalDate from,
            LocalDate to,
            int page,
            int pageSize
    );
}