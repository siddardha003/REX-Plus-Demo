// BeneficiaryDto.java
package com.rex.boilerplate.springboot.dto;

import lombok.*;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeneficiaryDto {
    private Long id;
    private java.util.UUID beneficiaryId;
    private java.util.UUID commodisId;
    private String firstName;
    private String lastName;
    private String academicTitle;
    private String listName;
    private LocalDate leavingDate;
    private Instant createdAt;
    private Instant updatedAt;
}
