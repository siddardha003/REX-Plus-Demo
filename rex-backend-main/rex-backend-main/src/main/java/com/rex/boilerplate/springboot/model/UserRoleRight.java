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
@Table(name = "userrole_to_right", schema = "rexbase")
public class UserRoleRight {

    @EmbeddedId
    private UserRoleRightId id;

    @MapsId("userroleId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userrole_id", nullable = false)
    private UserRole userrole;

    @MapsId("userrightId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userright_id", nullable = false)
    private UserRight userright;

    @Column(name = "activefrom", nullable = false)
    @ColumnDefault("'2020-01-01'")
    private LocalDate activeFrom;

    @Column(name = "activethrough", nullable = false)
    @ColumnDefault("'2999-12-31'")
    private LocalDate activeThrough;

    @Type(PostgreSQLRangeType.class)
    @Column(name = "active_range", columnDefinition = "daterange", insertable = false, updatable = false)
    private Range<LocalDate> activeRange;
}
