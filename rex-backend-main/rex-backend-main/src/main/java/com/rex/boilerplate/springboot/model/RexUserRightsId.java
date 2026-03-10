package com.rex.boilerplate.springboot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import java.io.Serializable;

@Data
@Embeddable
public class RexUserRightsId implements Serializable {
    @Column(name = "rexuser_id")
    private Long rexuserId;
    @Column(name = "rightcode")
    private String rightcode;
}
