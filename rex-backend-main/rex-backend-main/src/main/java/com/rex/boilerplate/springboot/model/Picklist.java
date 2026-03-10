package com.rex.boilerplate.springboot.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

/**
 * User-specific picklists (e.g., favorites).
 */
@Entity
@Table(name = "picklist", schema = "rexbase")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Picklist {

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

    @Id
    @SequenceGenerator(name = "GEN_Picklist", sequenceName = "picklist_id_seq", schema = "rexbase", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_Picklist")
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    /** Picklist display name */
    @Column(name = "displayname", nullable = false)
    private String displayName;

    /** Owner beneficiary account */
    @Column(name = "beneficiary_account_id", nullable = false)
    private Long beneficiaryAccountId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "beneficiary_account_id", insertable = false, updatable = false)
    private BeneficiaryAccount beneficiaryAccount;

    /** Order for display/sorting */
    @Column(name = "sort_id", nullable = false, columnDefinition = "SMALLINT")
    private Short sortId;
}
