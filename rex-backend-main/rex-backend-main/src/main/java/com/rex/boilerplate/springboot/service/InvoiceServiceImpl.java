package com.rex.boilerplate.springboot.service;

import com.rex.boilerplate.springboot.dto.DelegatedInvoiceDTO;
import com.rex.boilerplate.springboot.dto.DelegatedInvoiceMetaDTO;
import com.rex.boilerplate.springboot.dto.DelegatedInvoiceResponseDTO;
import com.rex.boilerplate.springboot.model.InvoiceResponsibility;
import com.rex.boilerplate.springboot.model.InvoiceStatus;
import com.rex.boilerplate.springboot.model.RexUser;
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
import java.math.BigDecimal;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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

        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Object[]> results =
                responsibilityRepository.findDelegatedInvoices(
                        rexUserId,
                        from,
                        to,
                        pageable
                );

        List<DelegatedInvoiceDTO> invoices = new ArrayList<>();

        for (Object[] row : results.getContent()) {

            Long id = (Long) row[0];
            String vendor = (String) row[1];
            String title = (String) row[2];
            LocalDate documentDate = (LocalDate) row[3];
            String number = (String) row[4];
            BigDecimal total = (BigDecimal) row[5];
            String currency = (String) row[6];
            Long delegateId = (Long) row[7];

            RexUser delegateUser = rexUserRepository
                    .findById(delegateId)
                    .orElseThrow();


            String status = statusRepository
                    .findTopByInvoiceIdOrderByCreatedAtDesc(id)
                    .map(InvoiceStatus::getStatus)
                    .orElse("Pending");

            DelegatedInvoiceDTO dto = DelegatedInvoiceDTO.builder()
                    .id(id)
                    .vendorName(vendor)
                    .invoiceTitle(title)
                    .invoiceDate(documentDate)
                    .invoiceNumber(number)
                    .invoiceTotal(total)
                    .currency(currency)
                    .status(status)
                    .delegatedUserId(delegateId)
                    .delegatedTo(delegateUser.getFullname())
                    .build();

            invoices.add(dto);
        }

        DelegatedInvoiceMetaDTO meta = DelegatedInvoiceMetaDTO.builder()
                .rexUserId(rexUserId)
                .from(from.toString())
                .to(to.toString())
                .page(page)
                .pageSize(pageSize)
                .build();

        return DelegatedInvoiceResponseDTO.builder()
                .meta(meta)
                .data(invoices)
                .build();
    }
}