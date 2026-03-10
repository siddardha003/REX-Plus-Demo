package com.rex.boilerplate.springboot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "deputyright_assignment", schema = "rexbase")
@IdClass(DeputyRightAssignmentId.class)
public class DeputyRightAssignment {

    @Id
    @Column(name = "deputy_assignment_id", nullable = false)
    private Long deputyAssignmentId;

    @Id
    @Column(name = "deputyright_id", nullable = false)
    private Long deputyRightId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deputy_assignment_id", nullable = false)
    private DeputyAssignment deputyAssignment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deputyright_id", nullable = false)
    private DeputyRight deputyRight;
}
