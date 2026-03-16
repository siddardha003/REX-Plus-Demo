package com.rex.boilerplate.springboot.service;

import com.rex.boilerplate.springboot.dto.DelegatedInvoiceDTO;
import com.rex.boilerplate.springboot.dto.DelegatedInvoiceMetaDTO;
import com.rex.boilerplate.springboot.dto.DelegatedInvoiceResponseDTO;
import com.rex.boilerplate.springboot.model.InvoiceResponsibility;
import com.rex.boilerplate.springboot.model.InvoiceStatus;
import com.rex.boilerplate.springboot.model.Invoices;
import com.rex.boilerplate.springboot.repository.InvoiceRepository;
import com.rex.boilerplate.springboot.repository.InvoiceResponsibilityRepository;
import com.rex.boilerplate.springboot.repository.InvoiceStatusRepository;
import com.rex.boilerplate.springboot.repository.RexUserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
@Transactional
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceResponsibilityRepository responsibilityRepository;
    private final InvoiceStatusRepository statusRepository;
    private final RexUserRepository rexUserRepository;
    private final InvoiceRepository invoiceRepository;

    @Override
    public void updateInvoiceStatus(Long invoiceId, String status) {

        Long rexUserId = 1L; // temporary as mentioned

        OffsetDateTime now = OffsetDateTime.now();

        Optional<InvoiceResponsibility> responsibility =
                responsibilityRepository
                        .findByInvoiceIdAndRexuserIdAndIsActiveTrue(
                                invoiceId,
                                rexUserId
                        );

        if (responsibility.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "User is not allowed to process this invoice"
            );
        }

        String creatorName = rexUserRepository
                .findById(rexUserId)
                .orElseThrow(() -> new RuntimeException("Rex user not found"))
                .getFullname();

        Optional<InvoiceStatus> latestStatus =
                statusRepository.findTopByInvoiceIdOrderByCreatedAtDesc(invoiceId);

        if (latestStatus.isPresent() &&
                latestStatus.get().getStatus().equals(status)) {
            throw new RuntimeException("Invoice already has this status");
        }

        InvoiceStatus invoiceStatus = InvoiceStatus.builder()
                .invoiceId(invoiceId)
                .status(status)
                .statusCreator(creatorName)
                .createdAt(now)
                .build();

        statusRepository.save(invoiceStatus);
    }

    @Override
    public DelegatedInvoiceResponseDTO getDelegatedInvoices(
            Boolean isActive,
            LocalDate from,
            LocalDate to,
            int page,
            int pageSize
    ) {

        Long rexUserId = 1L; // temporary

        List<InvoiceResponsibility> responsibilities =
                responsibilityRepository.findByRexuserIdAndIsActiveFalse(rexUserId);

        List<DelegatedInvoiceDTO> invoices = new ArrayList<>();

        for (InvoiceResponsibility responsibility : responsibilities) {

            Long invoiceId = responsibility.getInvoiceId();

            Optional<InvoiceResponsibility> activeResponsibility =
                    responsibilityRepository.findByInvoiceIdAndIsActiveTrueAndDelegateIdIsNotNull(invoiceId);

            if (activeResponsibility.isEmpty()) {
                continue;
            }

            Long activeUserId = activeResponsibility.get().getDelegateId();

            if (activeUserId == null) {
                continue;
            }

            if (activeUserId.equals(rexUserId)) {
                continue;
            }

            Invoices invoice = invoiceRepository
                    .findById(invoiceId)
                    .orElseThrow(() -> new RuntimeException("Invoice not found"));

            String status = statusRepository
                    .findTopByInvoiceIdOrderByCreatedAtDesc(invoiceId)
                    .map(InvoiceStatus::getStatus)
                    .orElse("Pending");

            DelegatedInvoiceDTO dto = DelegatedInvoiceDTO.builder()
                    .id(invoice.getId())
                    .vendorName(invoice.getVendor())
                    .invoiceTitle(invoice.getDocumentName())
                    .invoiceDate(invoice.getSupplyDate())
                    .invoiceNumber(invoice.getDocumentNumber())
                    .invoiceTotal(invoice.getInvoiceTotalGross())
                    .currency(invoice.getCurrency())
                    .status(status)
                    .build();

            invoices.add(dto);
        }

        DelegatedInvoiceMetaDTO meta = DelegatedInvoiceMetaDTO.builder()
                .rexUserId(rexUserId)
                .from(String.valueOf(from))
                .to(String.valueOf(to))
                .page(page)
                .pageSize(pageSize)
                .build();

        return DelegatedInvoiceResponseDTO.builder()
                .meta(meta)
                .data(invoices)
                .build();
    }
}