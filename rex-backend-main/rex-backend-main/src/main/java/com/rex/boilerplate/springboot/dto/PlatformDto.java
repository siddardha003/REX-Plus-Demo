package com.rex.boilerplate.springboot.dto;

import lombok.*;

import java.time.Instant;
import java.util.UUID;

/**
 * Data Transfer Objects for REXBASE entities
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlatformDto {
    private Long id;
    private UUID platformUuid;
    private String displayName;
    private java.time.LocalDate activeFrom;
    private java.time.LocalDate activeThrough;
    private Instant createdAt;
    private Instant updatedAt;
}
