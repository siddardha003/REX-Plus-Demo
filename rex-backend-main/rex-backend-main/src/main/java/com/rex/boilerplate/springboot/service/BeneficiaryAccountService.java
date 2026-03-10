package com.rex.boilerplate.springboot.service;

import com.rex.boilerplate.springboot.dto.BeneficiaryAccountDto;
import java.util.List;

/**
 * Service interface for managing BeneficiaryAccount entities.
 */
public interface BeneficiaryAccountService {

    /**
     * Fetch all beneficiary accounts.
     * 
     * @return list of BeneficiaryAccountDto
     */
    List<BeneficiaryAccountDto> fetchAll();

    /**
     * Fetch all beneficiary accounts for a given platform.
     * 
     * @param platformId the platform ID
     * @return list of BeneficiaryAccountDto
     */
    List<BeneficiaryAccountDto> fetchByPlatformId(Long platformId);

    /**
     * Fetch a single beneficiary account by its ID.
     * 
     * @param id the beneficiary account ID
     * @return the BeneficiaryAccountDto
     */
    BeneficiaryAccountDto getById(Long id);

    /**
     * Create a new beneficiary account.
     * 
     * @param dto data for the new account
     * @return the created BeneficiaryAccountDto
     */
    BeneficiaryAccountDto create(BeneficiaryAccountDto dto);

    /**
     * Update an existing beneficiary account.
     * 
     * @param id  the ID of the account to update
     * @param dto updated data
     * @return the updated BeneficiaryAccountDto
     */
    BeneficiaryAccountDto update(Long id, BeneficiaryAccountDto dto);

    /**
     * Delete a beneficiary account.
     * 
     * @param id the ID of the account to delete
     */
    void delete(Long id);
}
