package com.rex.boilerplate.springboot.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ProjectLeaderDto {
    private Long id;
    private Long projectId;
    private Long rexUserId;
    private LocalDate activeFrom;
    private LocalDate activeThrough;
}
