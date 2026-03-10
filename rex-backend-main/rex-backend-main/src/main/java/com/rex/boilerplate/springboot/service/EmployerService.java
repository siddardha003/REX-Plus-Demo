package com.rex.boilerplate.springboot.service;

import com.rex.boilerplate.springboot.dto.EmployerDto;
import java.util.List;

/**
 * Service interface for managing Employer entities.
 */
public interface EmployerService {

    /**
     * Fetch all employers across platforms.
     * @return list of EmployerDto
     */
    List<EmployerDto> fetchAll();

    /**
     * Fetch all employers for a specific platform.
     * @param platformId the platform ID
     * @return list of EmployerDto
     */
    List<EmployerDto> fetchByPlatformId(Long platformId);

    /**
     * Fetch a single employer by its ID.
     * @param id the employer ID
     * @return the EmployerDto
     */
    EmployerDto getById(Long id);

    /**
     * Create a new employer.
     * @param dto data for the new employer
     * @return the created EmployerDto
     */
    EmployerDto create(EmployerDto dto);

    /**
     * Update an existing employer.
     * @param id the ID of the employer to update
     * @param dto updated data
     * @return the updated EmployerDto
     */
    EmployerDto update(Long id, EmployerDto dto);

    /**
     * Delete an employer.
     * @param id the ID of the employer to delete
     */
    void delete(Long id);
}
