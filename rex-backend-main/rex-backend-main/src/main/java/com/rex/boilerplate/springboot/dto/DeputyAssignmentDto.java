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
public class DeputyAssignmentDto {
    private Long id;
    private LocalDate validFrom;
    private LocalDate validThrough;
    private Long beneficiaryAccountId;
    private Long deputyId;
    private Instant createdAt;
    private Instant updatedAt;
}
