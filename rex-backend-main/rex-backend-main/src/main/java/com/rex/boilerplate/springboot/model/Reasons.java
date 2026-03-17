package com.rex.boilerplate.springboot.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "reasons", schema = "invoice")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reasons {

    @Id
    @SequenceGenerator(
            name = "GEN_Reason",
            sequenceName = "rex_invoice_reason_id_seq",
            schema = "invoice",
            allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_Reason")
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "display_text", columnDefinition = "jsonb")
    private String displayText;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "platform_id", nullable = false)
    private Platform platform;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "occasion_id", nullable = false)
    private Occasions occasion;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;
}