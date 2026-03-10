package com.rex.boilerplate.springboot.service;

import com.rex.boilerplate.springboot.dto.PicklistDto;
import java.util.List;

/**
 * Service interface for managing Picklist entities.
 */
public interface PicklistService {

    /**
     * Fetch all picklists.
     * @return list of PicklistDto
     */
    List<PicklistDto> fetchAll();

    /**
     * Fetch picklists for a specific beneficiary account.
     * @param beneficiaryAccountId the account ID
     * @return list of PicklistDto
     */
    List<PicklistDto> fetchByBeneficiaryAccountId(Long beneficiaryAccountId);

    /**
     * Fetch a single picklist by its ID.
     * @param id the picklist ID
     * @return the PicklistDto
     */
    PicklistDto getById(Long id);

    /**
     * Create a new picklist.
     * @param dto data for the new picklist
     * @return the created PicklistDto
     */
    PicklistDto create(PicklistDto dto);

    /**
     * Update an existing picklist.
     * @param id the ID of the picklist to update
     * @param dto updated data
     * @return the updated PicklistDto
     */
    PicklistDto update(Long id, PicklistDto dto);

    /**
     * Delete a picklist.
     * @param id the ID of the picklist to delete
     */
    void delete(Long id);
}
