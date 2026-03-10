package com.rex.boilerplate.springboot.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "beneficiary_account", schema = "rexbase")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeneficiaryAccount {

    @Id
    @Column(name = "beneficiary_id", nullable = false)
    private Long id;

    @Column(name = "platform_id", nullable = false)
    private Long platformId;

    @Column(name = "delegation_permission")
    private Boolean delegationPermission; // DB default false; ensure non-null on persist below

    @Column(name = "keycloak_userid", nullable = false)
    private java.util.UUID keycloakUserid;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt; // timestamptz(6)

    @Column(name = "updated_at")
    private Instant updatedAt; // timestamptz(6)

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = Instant.now();
        }
        if (delegationPermission == null) {
            delegationPermission = Boolean.FALSE;
        }
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = Instant.now();
        if (delegationPermission == null) {
            delegationPermission = Boolean.FALSE;
        }
    }
}
