package com.rex.boilerplate.springboot.service;

import com.rex.boilerplate.springboot.dto.DeputyRightAssignmentDto;
import java.util.List;

/**
 * Service interface for managing DeputyRightAssignment entities.
 */
public interface DeputyRightAssignmentService {

    /**
     * Fetch all right assignments for a given deputy assignment.
     * @param deputyAssignmentId the deputy assignment ID
     * @return list of DeputyRightAssignmentDto
     */
    List<DeputyRightAssignmentDto> fetchByDeputyAssignmentId(Long deputyAssignmentId);

    /**
     * Create a new deputy right assignment.
     * @param dto data for the new right assignment
     * @return the created DeputyRightAssignmentDto
     */
    DeputyRightAssignmentDto create(DeputyRightAssignmentDto dto);

    /**
     * Delete a deputy right assignment.
     * @param id the ID of the right assignment to delete
     */
    void delete(Long id);
}
