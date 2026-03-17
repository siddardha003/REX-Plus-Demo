package com.rex.boilerplate.springboot.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Table(name = "invoices", schema = "invoice")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Invoices {

    @Id
    @SequenceGenerator(
            name = "rex_invoice_seq",
            sequenceName = "rex_invoice_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rex_invoice_seq")
    @Column(name = "id")
    private Long id;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @Column(name = "released_at")
    private OffsetDateTime releasedAt;

    @Column(name = "document_number", nullable = false)
    private String documentNumber;

    @Column(name = "document_link")
    private String documentLink;

    @Column(name = "document_type", nullable = false)
    private String documentType;

    @Column(name = "document_name", nullable = false)
    private String documentName;

    @Column(name = "supply_date")
    private LocalDate supplyDate;

    @Column(name = "supply_month", insertable = false, updatable = false)
    private Integer supplyMonth;

    @Column(name = "payment_date")
    private LocalDate paymentDate;

    @Column(name = "accounting_text", nullable = false)
    private String accountingText;

    @Column(name = "recipient_number")
    private String recipientNumber;

    @Column(name = "recipient", nullable = false)
    private String recipient;

    @Column(name = "vendor_number")
    private String vendorNumber;

    @Column(name = "vendor", nullable = false)
    private String vendor;

    @Column(name = "vendor_invoice_number")
    private String vendorInvoiceNumber;

    @Column(name = "invoicetotal_gross", precision = 10, scale = 2)
    private BigDecimal invoiceTotalGross;

    @Column(name = "invoicetotal_net", precision = 10, scale = 2)
    private BigDecimal invoiceTotalNet;

    @Column(name = "invoicetotal_vat", precision = 10, scale = 2)
    private BigDecimal invoiceTotalVat;

    @Column(name = "currency", length = 5)
    private String currency;

    @Column(name = "payrolltaxtion_required")
    private Boolean payrollTaxationRequired;

    @Column(name = "customfield_1")
    private String customfield1;

    @Column(name = "customfield_2")
    private String customfield2;

    @Column(name = "customfield_3")
    private String customfield3;

    @Column(name = "customfield_4")
    private String customfield4;

    @Column(name = "customfield_5")
    private String customfield5;

    @Column(name = "customfield_6")
    private String customfield6;

    @Column(name = "customfield_7")
    private String customfield7;

    @Column(name = "customfield_8")
    private String customfield8;

    @Column(name = "customfield_9")
    private String customfield9;

    @Column(name = "customfield_10")
    private String customfield10;

    @Column(name = "customfield_11")
    private String customfield11;

    @Column(name = "customfield_12")
    private String customfield12;

    @Column(name = "customfield_13")
    private String customfield13;

    @Column(name = "customfield_14")
    private String customfield14;

    @Column(name = "customfield_15")
    private String customfield15;

    @Column(name = "customfield_16")
    private String customfield16;

    @Column(name = "customfield_17")
    private String customfield17;

    @Column(name = "customfield_18")
    private String customfield18;

    @Column(name = "customfield_19")
    private String customfield19;

    @Column(name = "customfield_20")
    private String customfield20;

    @Column(name = "project_number_id", nullable = false)
    private Long projectNumberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "events_id", foreignKey = @ForeignKey(name = "fk_invoice_event"))
    private Events event;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "occasions_id", foreignKey = @ForeignKey(name = "fk_invoice_occasion"))
    private Occasions occasion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reasons_id", foreignKey = @ForeignKey(name = "fk_invoice_reason"))
    private Reasons reason;

    @Column(name = "note", columnDefinition = "TEXT")
    private String note;

    @Column(name = "document_date")
    private LocalDate documentDate;
}