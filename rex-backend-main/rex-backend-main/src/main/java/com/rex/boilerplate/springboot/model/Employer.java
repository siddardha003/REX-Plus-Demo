package com.rex.boilerplate.springboot.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;

import io.hypersistence.utils.hibernate.type.range.PostgreSQLRangeType;
import io.hypersistence.utils.hibernate.type.range.Range;
import org.hibernate.annotations.Type;

/**
 * Employer entity, tied to a Platform tenant.
 */
@Entity
@Table(name = "employer", schema = "rexbase")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employer {

    @Id
    @SequenceGenerator(name = "GEN_Employer", sequenceName = "employer_id_seq", schema = "rexbase", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_Employer")
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    /** Business UUID for the employer */
    @Column(name = "employer_uuid", nullable = false)
    private java.util.UUID employerUuid;

    @Column(name = "platform_id", nullable = false)
    private Long platformId;

    /** Tenant platform */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "platform_id", insertable = false, updatable = false)
    private Platform platform;

    @Column(name = "displayname", nullable = false)
    private String displayName;

    @Column(name = "activefrom", nullable = false)
    private LocalDate activeFrom;

    @Column(name = "activethrough")
    private LocalDate activeThrough;

    @Type(PostgreSQLRangeType.class)
    @Column(name = "activeperiod", columnDefinition = "daterange", insertable = false, updatable = false)
    private Range<LocalDate> activePeriod;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;
}
