package com.rex.boilerplate.springboot.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Types of guests for event or picklist notifications.
 */
@Entity
@Table(name = "guesttype", schema = "rexbase")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GuestType {

    @Id
    @SequenceGenerator(name = "GEN_GuestType", sequenceName = "guesttype_id_seq", schema = "rexbase", allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_GuestType")
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    /**
     * Code for the guest type (e.g. VIP, STANDARD)
     */
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    /**
     * Default display value in the primary language
     */
    @Column(name = "display_value", nullable = false)
    private String displayValue;

    /**
     * Indicates if this guest type is active
     */
    @Column(name = "active", nullable = false)
    private Boolean active;
}
