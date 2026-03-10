package com.rex.boilerplate.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeputyRightAssignmentId implements Serializable {
    private Long deputyAssignmentId;
    private Long deputyRightId;
}
