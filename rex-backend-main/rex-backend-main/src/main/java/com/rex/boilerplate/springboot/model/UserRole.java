package com.rex.boilerplate.springboot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import io.hypersistence.utils.hibernate.type.range.PostgreSQLRangeType;
import io.hypersistence.utils.hibernate.type.range.Range;
import org.hibernate.annotations.Type;

@Getter
@Setter
@Entity
@DynamicInsert
@Table(name = "userrole", schema = "rexbase")
public class UserRole {

    @Id
    @SequenceGenerator(name = "GEN_UserRole", sequenceName = "userrole_id_seq", schema = "rexbase", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_UserRole")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "rolename_de", nullable = false)
    private String rolenameDe;

    @Column(name = "rolename_en", nullable = false)
    private String rolenameEn;

    @Column(name = "activefrom", nullable = false)
    @ColumnDefault("'2020-01-01'")
    private LocalDate activeFrom;

    @Column(name = "activethrough", nullable = false)
    @ColumnDefault("'2999-12-31'")
    private LocalDate activeThrough;

    @Column(name = "created_at", nullable = false)
    @ColumnDefault("CURRENT_TIMESTAMP(6)")
    private OffsetDateTime createdAt;

    @Type(PostgreSQLRangeType.class)
    @Column(name = "active_range", columnDefinition = "daterange", insertable = false, updatable = false)
    private Range<LocalDate> activeRange;
}
