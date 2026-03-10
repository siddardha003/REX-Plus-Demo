package com.rex.boilerplate.springboot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@Table(name = "userright", schema = "rexbase")
public class UserRight {

    @Id
    @SequenceGenerator(name = "GEN_UserRight", sequenceName = "userright_id_seq", schema = "rexbase", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_UserRight")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "rightname_de", nullable = false)
    private String rightnameDe;

    @Column(name = "rightname_en", nullable = false)
    private String rightnameEn;

    @Column(name = "rightcode", nullable = false)
    private String rightcode;

    @Column(name = "activefrom", nullable = false)
    @ColumnDefault("'2020-01-01'")
    private LocalDate activeFrom;

    @Column(name = "activethrough", nullable = false)
    @ColumnDefault("'2999-12-31'")
    private LocalDate activeThrough;

    @Column(name = "created_at", nullable = false)
    @ColumnDefault("now()")
    private OffsetDateTime createdAt;

    @Type(PostgreSQLRangeType.class)
    @Column(name = "active_range", columnDefinition = "daterange", insertable = false, updatable = false)
    private Range<LocalDate> activeRange;
}
