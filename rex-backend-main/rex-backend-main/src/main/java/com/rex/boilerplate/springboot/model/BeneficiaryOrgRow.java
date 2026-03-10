package com.rex.boilerplate.springboot.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeneficiaryOrgRow {

    private Long platformId;
    private Long beneficiaryId;
    private String platform;
    private String employer;
    private String listname;
    private LocalDate leavingdate;
    private String personnelnumbersystem;
    private String personnelnumberpayroll;
    private String identity_tag;
    private String companysite;
    private String orgunit;
    private String costcenter;
}
