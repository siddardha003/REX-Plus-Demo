package com.rex.boilerplate.springboot.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDate;

import io.hypersistence.utils.hibernate.type.range.PostgreSQLRangeType;
import io.hypersistence.utils.hibernate.type.range.Range;
import org.hibernate.annotations.Type;

@Getter
@Setter
@Entity
@DynamicInsert
@Table(name = "rexuser_to_role", schema = "rexbase")
public class RexUserRole {

    @EmbeddedId
    private RexUserRoleId id;

    @MapsId("rexuserId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rexuser_id", nullable = false)
    private RexUser rexuser;

    @MapsId("userroleId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userrole_id", nullable = false)
    private UserRole userrole;

    @Column(name = "activethrough", nullable = false)
    @ColumnDefault("'2999-12-31'")
    private LocalDate activeThrough;

    @Type(PostgreSQLRangeType.class)
    @Column(name = "active_range", columnDefinition = "daterange", insertable = false, updatable = false)
    private Range<LocalDate> activeRange;
}
