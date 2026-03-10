package com.rex.boilerplate.springboot.service;

import com.rex.boilerplate.springboot.dto.DeputyAssignmentDto;
import java.util.List;

/**
 * Service interface for managing DeputyAssignment entities.
 */
public interface DeputyAssignmentService {

    /**
     * Fetch all assignments for a given beneficiary account.
     * 
     * @param beneficiaryAccountId the ID of the beneficiary account
     * @return list of DeputyAssignmentDto
     */
    List<DeputyAssignmentDto> fetchByBeneficiaryAccountId(Long beneficiaryAccountId);

    /**
     * Create a new deputy assignment.
     * 
     * @param dto data for the new assignment
     * @return the created DeputyAssignmentDto
     */
    DeputyAssignmentDto create(DeputyAssignmentDto dto);

    /**
     * Update an existing deputy assignment.
     * 
     * @param id  the ID of the assignment to update
     * @param dto updated data
     * @return the updated DeputyAssignmentDto
     */
    DeputyAssignmentDto update(Long id, DeputyAssignmentDto dto);

    /**
     * Delete a deputy assignment.
     * 
     * @param id the ID of the assignment to delete
     */
    void delete(Long id);
}
