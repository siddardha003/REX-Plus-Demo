package com.rex.boilerplate.springboot.service;

import com.rex.boilerplate.springboot.dto.PicklistToBeneficiaryDto;
import java.util.List;

/**
 * Service interface for managing PicklistToBeneficiary entities.
 */
public interface PicklistToBeneficiaryService {

    /**
     * Fetch all PicklistToBeneficiary links for a specific picklist.
     * @param picklistId the picklist ID
     * @return list of PicklistToBeneficiaryDto
     */
    List<PicklistToBeneficiaryDto> fetchByPicklistId(Long picklistId);

    /**
     * Create a new PicklistToBeneficiary link.
     * @param dto data for the new link
     * @return the created PicklistToBeneficiaryDto
     */
    PicklistToBeneficiaryDto create(PicklistToBeneficiaryDto dto);

    /**
     * Delete a PicklistToBeneficiary link by its ID.
     * @param id the link ID
     */
    void delete(Long id);
}
