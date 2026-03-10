package com.rex.boilerplate.springboot.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;

import io.hypersistence.utils.hibernate.type.range.PostgreSQLRangeType;
import io.hypersistence.utils.hibernate.type.range.Range;
import org.hibernate.annotations.Type;

/**
 * Tenant platform entity.
 */
@Entity
@Table(name = "platform", schema = "rexbase")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Platform {

    @Id
    @SequenceGenerator(name = "GEN_Platform", sequenceName = "platform_id_seq", schema = "rexbase", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_Platform")
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "platform_uuid", nullable = false)
    private java.util.UUID platformUuid;

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
