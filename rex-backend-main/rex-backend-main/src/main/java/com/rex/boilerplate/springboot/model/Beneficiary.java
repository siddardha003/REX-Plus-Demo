// Beneficiary.java
package com.rex.boilerplate.springboot.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "beneficiary", schema = "rexbase")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Beneficiary {

    @Id
    @SequenceGenerator(name = "GEN_Beneficiary", sequenceName = "beneficiary_id_seq", schema = "rexbase", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_Beneficiary")
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    /** Business UUID for the person */
    @Column(name = "beneficiaryid", nullable = false)
    private java.util.UUID beneficiaryId;

    @Column(name = "commodisid", nullable = false)
    private java.util.UUID commodisId;

    @Column(name = "firstname", nullable = false)
    private String firstName;

    @Column(name = "lastname", nullable = false)
    private String lastName;

    @Column(name = "academictitle")
    private String academicTitle;

    @Column(name = "listname", insertable = false, updatable = false)
    private String listName;

    /** when this person leaves the organization */
    @Column(name = "leavingdate")
    private LocalDate leavingDate;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;
}
