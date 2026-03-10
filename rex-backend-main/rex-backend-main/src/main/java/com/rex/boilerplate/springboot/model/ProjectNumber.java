package com.rex.boilerplate.springboot.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Type;
import io.hypersistence.utils.hibernate.type.range.PostgreSQLRangeType;
import io.hypersistence.utils.hibernate.type.range.Range;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Represents a project within a platform.
 */
@Entity
@Table(name = "projectnumbers", schema = "rexbase")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class ProjectNumber {

    @Id
    @SequenceGenerator(name = "GEN_ProjectNumber", sequenceName = "projectnumber_id_seq", schema = "rexbase", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_ProjectNumber")
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "platform_id", nullable = false)
    private Long platformId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "platform_id", insertable = false, updatable = false)
    private Platform platform;

    @Column(name = "projectid", nullable = false)
    private UUID projectId;

    @Column(name = "external_projectid")
    private String externalProjectId;

    @Column(name = "projectnumber", nullable = false, length = 25)
    private String projectNumber;

    @Column(name = "projecttitle", nullable = false)
    private String projectTitle;

    @Column(name = "projectsubtitle")
    private String projectSubtitle;

    @Column(name = "description")
    private String description;

    @Column(name = "activefrom", nullable = false)
    @ColumnDefault("'2020-01-01'")
    private LocalDate activeFrom;

    @Column(name = "activethrough", nullable = false)
    @ColumnDefault("'2999-12-31'")
    private LocalDate activeThrough;

    @Type(PostgreSQLRangeType.class)
    @Column(name = "active_range", columnDefinition = "daterange", insertable = false, updatable = false)
    private Range<LocalDate> activeRange;

    @Column(name = "created_at", nullable = false, updatable = false)
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;
}
