package com.rex.boilerplate.springboot.service;

import com.rex.boilerplate.springboot.dto.GuestDto;
import java.util.List;

/**
 * Service interface for managing Guest entities.
 */
public interface GuestService {

    /**
     * Fetch all guests.
     * @return list of GuestDto
     */
    List<GuestDto> fetchAll();

    /**
     * Fetch all guests for a specific platform.
     * @param platformId the platform ID
     * @return list of GuestDto
     */
    List<GuestDto> fetchByPlatformId(Long platformId);

    /**
     * Fetch a single guest by its ID.
     * @param id the guest ID
     * @return the GuestDto
     */
    GuestDto getById(Long id);

    /**
     * Create a new guest.
     * @param dto data for the new guest
     * @return the created GuestDto
     */
    GuestDto create(GuestDto dto);

    /**
     * Update an existing guest.
     * @param id the ID of the guest to update
     * @param dto updated data
     * @return the updated GuestDto
     */
    GuestDto update(Long id, GuestDto dto);

    /**
     * Delete a guest.
     * @param id the ID of the guest to delete
     */
    void delete(Long id);
}
