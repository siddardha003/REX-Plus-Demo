package com.rex.boilerplate.springboot.service;

import com.rex.boilerplate.springboot.dto.BeneficiaryToEmployerDto;
import java.util.List;

/**
 * Service interface for managing Beneficiary to Employer relationships.
 */
public interface BeneficiaryToEmployerService {

    /**
     * Fetch all link records for a given beneficiary ID.
     *
     * @param beneficiaryId the ID of the beneficiary
     * @return list of BeneficiaryToEmployerDto
     */
    List<BeneficiaryToEmployerDto> fetchByBeneficiaryId(Long beneficiaryId);

    /**
     * Fetch all link records for a given employer ID.
     *
     * @param employerId the ID of the employer
     * @return list of BeneficiaryToEmployerDto
     */
    List<BeneficiaryToEmployerDto> fetchByEmployerId(Long employerId);
}
