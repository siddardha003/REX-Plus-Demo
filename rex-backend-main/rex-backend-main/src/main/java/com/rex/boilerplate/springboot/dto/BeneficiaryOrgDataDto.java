package com.rex.boilerplate.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeneficiaryOrgDataDto {
    private Long id;
    private Long beneficiaryAccountId;
    private Long employerId;
    private LocalDate validFrom;
    private LocalDate validThrough;
    private String personnelNumberSystem;
    private String personnelNumberPayroll;
    private String identityTag;
    private String companySite;
    private String orgUnit;
    private String costCenter;
    private Instant createdAt;
    private Instant updatedAt;
}
