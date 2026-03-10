package com.rex.boilerplate.springboot.model;

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
public class PicklistMemberRow {

    private Long picklistId;
    private String memberType;   // e.g., "INTERNAL" or "EXERNAL" (as defined in the view)
    private String listname;
    private String companyname;
}

