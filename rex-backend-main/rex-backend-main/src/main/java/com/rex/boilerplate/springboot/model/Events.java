package com.rex.boilerplate.springboot.model;

import jakarta.persistence.*;
        import lombok.*;

        import java.time.Instant;

@Entity
@Table(name = "events", schema = "invoice")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Events {

    @Id
    @SequenceGenerator(
            name = "GEN_Event",
            sequenceName = "rex_event_id_seq",
            schema = "invoice",
            allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_Event")
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "event_name", nullable = false)
    private String eventName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "platform_id", nullable = false)
    private Platform platform;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;
}