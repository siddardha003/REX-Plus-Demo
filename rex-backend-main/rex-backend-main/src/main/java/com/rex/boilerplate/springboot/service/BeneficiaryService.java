package com.rex.boilerplate.springboot.service;

import com.rex.boilerplate.springboot.dto.BeneficiaryDto;
import com.rex.boilerplate.springboot.dto.BeneficiaryFilterRequest;
import com.rex.boilerplate.springboot.dto.BeneficiaryPageResponse;

public interface BeneficiaryService {
    BeneficiaryPageResponse fetchBeneficiaries(BeneficiaryFilterRequest filterRequest);

    BeneficiaryDto getBeneficiaryById(java.util.UUID id);
}
