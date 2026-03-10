package com.rex.boilerplate.springboot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Generated;

import org.hibernate.generator.EventType;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import io.hypersistence.utils.hibernate.type.range.PostgreSQLRangeType;
import io.hypersistence.utils.hibernate.type.range.Range;
import org.hibernate.annotations.Type;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Table(name = "rexuser", schema = "rexbase")
public class RexUser {

    @Id
    @SequenceGenerator(name = "GEN_RexUser", sequenceName = "rexuser_id_seq", schema = "rexbase", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_RexUser")
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "keycloak_userid", nullable = false)
    private KeycloakSim keycloakUserid;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "academictitle")
    private String academictitle;

    @Column(name = "organisation", nullable = false)
    private String organisation;

    @Column(name = "activefrom", nullable = false)
    @ColumnDefault("'2020-01-01'")
    private LocalDate activeFrom;

    @Column(name = "activethrough", nullable = false)
    @ColumnDefault("'2999-12-31'")
    private LocalDate activeThrough;

    @Column(name = "created_at", nullable = false)
    @ColumnDefault("now()")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @Generated(event = EventType.INSERT)
    @Column(name = "listname", insertable = false, updatable = false)
    private String listname;

    @Generated(event = EventType.INSERT)
    @Column(name = "fullname", insertable = false, updatable = false)
    private String fullname;

    @Type(PostgreSQLRangeType.class)
    @Column(name = "active_range", columnDefinition = "daterange", insertable = false, updatable = false)
    private Range<LocalDate> activeRange;
}
