package com.rex.boilerplate.springboot.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;

/**
 * Assignment of a deputy for a beneficiary account, valid over a date range.
 */
@Entity
@Table(name = "deputy_assignment", schema = "rexbase", uniqueConstraints = @UniqueConstraint(name = "deputy_assignment_unique", columnNames = {
        "beneficiary_account_id", "deputy_id" }))
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeputyAssignment {

    @Id
    @SequenceGenerator(name = "GEN_DeputyAssignment", sequenceName = "deputy_assignment_id_seq", schema = "rexbase", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_DeputyAssignment")
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "validfrom", nullable = false)
    private LocalDate validFrom;

    @Column(name = "validthrough", nullable = false)
    private LocalDate validThrough;

    @Column(name = "beneficiary_account_id", nullable = false)
    private Long beneficiaryAccountId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "beneficiary_account_id", insertable = false, updatable = false)
    private BeneficiaryAccount beneficiaryAccount;

    @Column(name = "deputy_id", nullable = false)
    private Long deputyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deputy_id", insertable = false, updatable = false)
    private RexUser deputy;
}
