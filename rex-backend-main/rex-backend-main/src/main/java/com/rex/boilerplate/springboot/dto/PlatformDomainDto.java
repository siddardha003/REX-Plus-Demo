package com.rex.boilerplate.springboot.dto;

import lombok.*;

/**
 * Data Transfer Objects for REXBASE entities
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlatformDomainDto {
    private Long id;
    private Long platformId;
    private String domainName;
}

