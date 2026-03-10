package com.rex.boilerplate.springboot.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
public class ProjectNumberDto {
    private Long id;
    private Long platformId;
    private UUID projectId;
    private String externalProjectId;
    private String projectNumber;
    private String projectTitle;
    private String projectSubtitle;
    private String description;
    private LocalDate activeFrom;
    private LocalDate activeThrough;
}
