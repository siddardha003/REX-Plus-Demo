package com.rex.boilerplate.springboot.service;

import com.rex.boilerplate.springboot.dto.PicklistDto;
import com.rex.boilerplate.springboot.exceptions.NotFoundException;
import com.rex.boilerplate.springboot.mapper.PicklistMapper;
import com.rex.boilerplate.springboot.model.Picklist;
import com.rex.boilerplate.springboot.repository.PicklistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PicklistServiceImpl implements PicklistService {

    private final PicklistRepository picklistRepository;
    private final PicklistMapper picklistMapper;

    @Override
    @Transactional(readOnly = true)
    public List<PicklistDto> fetchAll() {
        return picklistRepository.findAll().stream()
                .map(picklistMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PicklistDto> fetchByBeneficiaryAccountId(Long beneficiaryAccountId) {
        return picklistRepository.findAll().stream()
                .filter(p -> p.getBeneficiaryAccountId().equals(beneficiaryAccountId))
                .map(picklistMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PicklistDto getById(Long id) {
        Picklist entity = picklistRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Picklist not found with id: " + id));
        return picklistMapper.toDto(entity);
    }

    @Override
    @Transactional
    public PicklistDto create(PicklistDto dto) {
        Picklist entity = picklistMapper.toEntity(dto);
        entity.setId(null);
        Picklist saved = picklistRepository.save(entity);
        return picklistMapper.toDto(saved);
    }

    @Override
    @Transactional
    public PicklistDto update(Long id, PicklistDto dto) {
        Picklist existing = picklistRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Picklist not found with id: " + id));
        existing.setDisplayName(dto.getDisplayname());
        existing.setSortId(dto.getSortId());
        existing.setUpdatedAt(dto.getUpdatedAt());
        Picklist updated = picklistRepository.save(existing);
        return picklistMapper.toDto(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!picklistRepository.existsById(id)) {
            throw new NotFoundException("Picklist not found with id: " + id);
        }
        picklistRepository.deleteById(id);
    }
}
