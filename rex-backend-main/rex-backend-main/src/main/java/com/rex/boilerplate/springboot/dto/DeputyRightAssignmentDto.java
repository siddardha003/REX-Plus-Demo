package com.rex.boilerplate.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeputyRightAssignmentDto {
    private Long deputyAssignmentId;
    private Long deputyRightId;
}
