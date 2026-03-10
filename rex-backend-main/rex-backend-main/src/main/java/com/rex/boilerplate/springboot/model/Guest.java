package com.rex.boilerplate.springboot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "guest", schema = "rexbase")
public class Guest {

    @Id
    @SequenceGenerator(name = "GEN_Guest", sequenceName = "guest_id_seq", schema = "rexbase", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_Guest")
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    // rexbase.guest.creator_beneficiary_account_id UUID NOT NULL
    @Column(name = "creator_beneficiary_account_id", nullable = false)
    private Long creatorBeneficiaryAccountId;

    // rexbase.guest.organisationwide_guest BOOLEAN NOT NULL DEFAULT false
    @Column(name = "organisationwide_guest", nullable = false)
    private Boolean organisationwideGuest;

    // rexbase.guest.firstname VARCHAR(255) NOT NULL
    @Column(name = "firstname", nullable = false)
    private String firstname;

    // rexbase.guest.lastname VARCHAR(255) NOT NULL
    @Column(name = "lastname", nullable = false)
    private String lastname;

    // rexbase.guest.lastname VARCHAR(255) NOT NULL
    /** Generated: lastname + firstname */
    @Column(name = "listname", nullable = false, insertable = false, updatable = false)
    private String listname;

    // rexbase.guest.organization VARCHAR(255) NOT NULL
    @Column(name = "organization", nullable = false)
    private String organization;

    // rexbase.guest.created_at timestamptz(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    // rexbase.guest.updated_at timestamptz(6) NULL
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "default_guesttype_id", nullable = false)
    private GuestType defaultGuestType;
}
