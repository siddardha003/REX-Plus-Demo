package com.rex.boilerplate.springboot.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "invoice_responsibility", schema = "invoice")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceResponsibility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "invoice_id", nullable = false)
    private Long invoiceId;

    @Column(name = "rexuser_id", nullable = false)
    private Long rexuserId;

    @Column(name = "delegate_id")
    private Long delegateId;

    @Column(name = "delegated_at")
    private OffsetDateTime delegatedAt;

    @Column(name = "remarks")
    private String remarks;

    @Column(name = "responsible_from", nullable = false)
    private OffsetDateTime responsibleFrom;

    @Column(name = "responsible_through", nullable = false)
    private OffsetDateTime responsibleThrough;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "responsible_period", insertable = false, updatable = false)
    private String responsiblePeriod;
}