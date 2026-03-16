package com.rex.boilerplate.springboot.dto;

import lombok.*;

import java.time.Instant;
import java.util.UUID;

/**
 * Data Transfer Object for Platform
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlatformDto {

    private Long id;

    private UUID platformUuid;

    private String displayName;

    private Boolean active;

    private Instant createdAt;

    private Instant updatedAt;
}