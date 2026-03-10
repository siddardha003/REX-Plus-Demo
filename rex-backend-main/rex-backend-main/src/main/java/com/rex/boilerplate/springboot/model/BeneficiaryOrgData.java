package com.rex.boilerplate.springboot.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;

import io.hypersistence.utils.hibernate.type.range.PostgreSQLRangeType;
import io.hypersistence.utils.hibernate.type.range.Range;
import org.hibernate.annotations.Type;

/**
 * Time-bound organizational details for a BeneficiaryAccount.
 */
@Entity
@Table(name = "beneficiary_orgdata", schema = "rexbase")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeneficiaryOrgData {

    @Id
    @SequenceGenerator(name = "GEN_BeneficiaryOrgData", sequenceName = "beneficiary_orgdata_id_seq", schema = "rexbase", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_BeneficiaryOrgData")
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "beneficiary_id", nullable = false)
    private Long beneficiaryAccountId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "beneficiary_id", insertable = false, updatable = false)
    private BeneficiaryAccount beneficiaryAccount;

    @Column(name = "employer_id", nullable = false)
    private Long employerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employer_id", insertable = false, updatable = false)
    private Employer employer;

    @Type(PostgreSQLRangeType.class)
    @Column(name = "validityrange", columnDefinition = "daterange", insertable = false, updatable = false)
    private Range<LocalDate> validityRange;

    @Column(name = "validfrom", nullable = false)
    private LocalDate validFrom;

    @Column(name = "validthrough")
    private LocalDate validThrough;

    @Column(name = "personnelnumbersystem")
    private String personnelNumberSystem;

    @Column(name = "personnelnumberpayroll")
    private String personnelNumberPayroll;

    @Column(name = "identity_tag")
    private String identityTag;

    @Column(name = "companysite")
    private String companySite;

    @Column(name = "orgunit")
    private String orgUnit;

    @Column(name = "costcenter")
    private String costCenter;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;
}
