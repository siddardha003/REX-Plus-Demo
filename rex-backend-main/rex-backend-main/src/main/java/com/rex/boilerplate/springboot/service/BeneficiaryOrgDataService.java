package com.rex.boilerplate.springboot.service;

import com.rex.boilerplate.springboot.dto.BeneficiaryOrgDataDto;
import java.util.List;

/**
 * Service interface for managing BeneficiaryOrgData entities.
 */
public interface BeneficiaryOrgDataService {

    /**
     * Fetch all organizational data entries for a given beneficiary account.
     * 
     * @param beneficiaryAccountId the ID of the beneficiary account
     * @return list of BeneficiaryOrgDataDto
     */
    List<BeneficiaryOrgDataDto> fetchByBeneficiaryAccountId(Long beneficiaryAccountId);

    /**
     * Create a new organizational data record for a beneficiary.
     * 
     * @param dto the data to create
     * @return the created BeneficiaryOrgDataDto
     */
    BeneficiaryOrgDataDto create(BeneficiaryOrgDataDto dto);

    /**
     * Update an existing organizational data record.
     * 
     * @param id  the ID of the record to update
     * @param dto updated data
     * @return the updated BeneficiaryOrgDataDto
     */
    BeneficiaryOrgDataDto update(Long id, BeneficiaryOrgDataDto dto);

    /**
     * Delete an organizational data record by its ID.
     * 
     * @param id the ID of the record to delete
     */
    void delete(Long id);
}
