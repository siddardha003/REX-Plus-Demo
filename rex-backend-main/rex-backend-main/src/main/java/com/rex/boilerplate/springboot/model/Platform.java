package com.rex.boilerplate.springboot.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

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
    @SequenceGenerator(
            name = "GEN_Platform",
            sequenceName = "platform_id_seq",
            schema = "rexbase",
            allocationSize = 50
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_Platform")
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "platform_uuid", nullable = false)
    private UUID platformUuid;

    @Column(name = "displayname", nullable = false)
    private String displayName;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;
}