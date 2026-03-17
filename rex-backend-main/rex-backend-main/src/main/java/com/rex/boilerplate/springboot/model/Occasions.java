package com.rex.boilerplate.springboot.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "occasions", schema = "invoice")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Occasions {

    @Id
    @SequenceGenerator(
            name = "GEN_Occasion",
            sequenceName = "rex_invoice_occasion_id_seq",
            schema = "invoice",
            allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_Occasion")
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "display_text", columnDefinition = "jsonb")
    private String displayText;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "platform_id", nullable = false)
    private Platform platform;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;
}