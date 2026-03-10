package com.rex.boilerplate.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PicklistDto {
    private Long id;
    private Long beneficiaryAccountId;
    private String displayname;
    private Short sortId;
    private Instant createdAt;
    private Instant updatedAt;
}
