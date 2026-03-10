package com.rex.boilerplate.springboot.service;

import com.rex.boilerplate.springboot.dto.DeputyAssignmentDto;
import com.rex.boilerplate.springboot.exceptions.NotFoundException;
import com.rex.boilerplate.springboot.mapper.DeputyAssignmentMapper;
import com.rex.boilerplate.springboot.model.DeputyAssignment;
import com.rex.boilerplate.springboot.repository.DeputyAssignmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of DeputyAssignmentService.
 */
@Service
@RequiredArgsConstructor
public class DeputyAssignmentServiceImpl implements DeputyAssignmentService {

    private final DeputyAssignmentRepository repository;
    private final DeputyAssignmentMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public List<DeputyAssignmentDto> fetchByBeneficiaryAccountId(Long beneficiaryAccountId) {
        return repository.findAll().stream()
                .filter(a -> a.getBeneficiaryAccountId().equals(beneficiaryAccountId))
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public DeputyAssignmentDto create(DeputyAssignmentDto dto) {
        DeputyAssignment entity = mapper.toEntity(dto);
        entity.setId(null);
        DeputyAssignment saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional
    public DeputyAssignmentDto update(Long id, DeputyAssignmentDto dto) {
        DeputyAssignment existing = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("DeputyAssignment not found with id: " + id));
        existing.setValidFrom(dto.getValidFrom());
        existing.setValidThrough(dto.getValidThrough());
        existing.setBeneficiaryAccountId(dto.getBeneficiaryAccountId());
        existing.setDeputyId(dto.getDeputyId());
        existing.setUpdatedAt(dto.getUpdatedAt());
        DeputyAssignment updated = repository.save(existing);
        return mapper.toDto(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("DeputyAssignment not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
