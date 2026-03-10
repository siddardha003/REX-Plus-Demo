package com.rex.boilerplate.springboot.dto;

import lombok.*;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeneficiaryAccountDto {
    private Long id; // beneficiary_id
    private Long platformId; // platform_id
    private Boolean delegationPermission; // delegation_permission
    private java.util.UUID keycloakUserid; // keycloak_userid
    private Instant createdAt; // created_at
    private Instant updatedAt; // updated_at
}
