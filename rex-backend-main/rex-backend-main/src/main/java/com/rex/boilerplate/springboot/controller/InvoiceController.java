package com.rex.boilerplate.springboot.controller;

import com.rex.boilerplate.springboot.dto.DelegatedInvoiceResponseDTO;
import com.rex.boilerplate.springboot.dto.InvoiceStatusRequest;
import com.rex.boilerplate.springboot.service.InvoiceService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @PostMapping("/{invoiceId}/status")
    public ResponseEntity<?> updateInvoiceStatus(
            @PathVariable Long invoiceId,
            @RequestBody InvoiceStatusRequest request) {

        invoiceService.updateInvoiceStatus(invoiceId, request.getStatus());

        Map<String, String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Invoice rejected successfully.");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/delegated")
    public ResponseEntity<?> getDelegatedInvoices(
            @RequestParam(name = "is_active", defaultValue = "false") Boolean isActive,
            @RequestParam LocalDate from,
            @RequestParam LocalDate to,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(name = "page_size", defaultValue = "20") int pageSize
    ) {

        DelegatedInvoiceResponseDTO response =
                invoiceService.getDelegatedInvoices(isActive, from, to, page, pageSize);

        return ResponseEntity.ok(response);
    }
}