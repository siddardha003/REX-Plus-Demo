package com.rex.boilerplate.springboot.service;

import com.rex.boilerplate.springboot.dto.BeneficiaryAccountDto;
import com.rex.boilerplate.springboot.exceptions.NotFoundException;
import com.rex.boilerplate.springboot.mapper.BeneficiaryAccountMapper;
import com.rex.boilerplate.springboot.model.BeneficiaryAccount;
import com.rex.boilerplate.springboot.repository.BeneficiaryAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service implementation aligned to QA DDL for rexbase.beneficiary_account.
 */
@Service
@RequiredArgsConstructor
public class BeneficiaryAccountServiceImpl implements BeneficiaryAccountService {

    private final BeneficiaryAccountRepository accountRepository;
    private final BeneficiaryAccountMapper accountMapper;

    @Override
    @Transactional(readOnly = true)
    public List<BeneficiaryAccountDto> fetchAll() {
        return accountRepository.findAll().stream()
                .map(accountMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<BeneficiaryAccountDto> fetchByPlatformId(Long platformId) {
        return accountRepository.findAll().stream()
                .filter(a -> a.getPlatformId().equals(platformId))
                .map(accountMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public BeneficiaryAccountDto getById(Long id) {
        BeneficiaryAccount entity = accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("BeneficiaryAccount not found with id: " + id));
        return accountMapper.toDto(entity);
    }

    @Override
    @Transactional
    public BeneficiaryAccountDto create(BeneficiaryAccountDto dto) {
        if (dto.getId() == null) {
            throw new IllegalArgumentException("beneficiary_id (id) must be provided for creation; it is the PK");
        }
        if (dto.getKeycloakUserid() == null) {
            throw new IllegalArgumentException("keycloak_userid must be provided");
        }
        BeneficiaryAccount entity = accountMapper.toEntity(dto);
        BeneficiaryAccount saved = accountRepository.save(entity);
        return accountMapper.toDto(saved);
    }

    @Override
    @Transactional
    public BeneficiaryAccountDto update(Long id, BeneficiaryAccountDto dto) {
        BeneficiaryAccount existing = accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("BeneficiaryAccount not found with id: " + id));
        // Only fields defined in QA DDL
        existing.setPlatformId(dto.getPlatformId());
        existing.setDelegationPermission(dto.getDelegationPermission());
        existing.setKeycloakUserid(dto.getKeycloakUserid());
        // updatedAt handled by @PreUpdate in entity
        BeneficiaryAccount updated = accountRepository.save(existing);
        return accountMapper.toDto(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!accountRepository.existsById(id)) {
            throw new NotFoundException("BeneficiaryAccount not found with id: " + id);
        }
        accountRepository.deleteById(id);
    }
}
