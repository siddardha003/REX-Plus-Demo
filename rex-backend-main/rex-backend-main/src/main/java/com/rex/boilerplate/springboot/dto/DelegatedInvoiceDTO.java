package com.rex.boilerplate.springboot.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder
public class DelegatedInvoiceDTO {

    private Long id;
    private String vendorName;
    private String invoiceTitle;
    private LocalDate invoiceDate;
    private String invoiceNumber;
    private BigDecimal invoiceTotal;
    private String currency;
    private String status;
    private String delegatedTo;
    private Long delegatedUserId;
}