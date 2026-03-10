package com.rex.boilerplate.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GuestDto {

    private Long id;

    private Long creatorBeneficiaryAccountId;

    private Boolean organisationwideGuest;

    private String firstname;

    private String lastname;

    // DB-generated (read-only on the entity); included here for reads
    private String listname;

    private String organization;

    // SMALLINT -> Short in Java
    private Short defaultGuesttypeId;

    private Instant createdAt;

    private Instant updatedAt;
}
