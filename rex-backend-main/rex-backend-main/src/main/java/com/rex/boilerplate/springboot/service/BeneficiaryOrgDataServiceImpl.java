package com.rex.boilerplate.springboot.service;

import com.rex.boilerplate.springboot.dto.BeneficiaryOrgDataDto;
import com.rex.boilerplate.springboot.exceptions.NotFoundException;
import com.rex.boilerplate.springboot.mapper.BeneficiaryOrgDataMapper;
import com.rex.boilerplate.springboot.model.BeneficiaryOrgData;
import com.rex.boilerplate.springboot.repository.BeneficiaryOrgDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BeneficiaryOrgDataServiceImpl implements BeneficiaryOrgDataService {

    private final BeneficiaryOrgDataRepository orgDataRepository;
    private final BeneficiaryOrgDataMapper orgDataMapper;

    @Override
    @Transactional(readOnly = true)
    public List<BeneficiaryOrgDataDto> fetchByBeneficiaryAccountId(Long beneficiaryAccountId) {
        List<BeneficiaryOrgData> list = orgDataRepository.findAll().stream()
                .filter(o -> o.getBeneficiaryAccountId().equals(beneficiaryAccountId))
                .collect(Collectors.toList());
        return list.stream()
                .map(orgDataMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public BeneficiaryOrgDataDto create(BeneficiaryOrgDataDto dto) {
        BeneficiaryOrgData entity = orgDataMapper.toEntity(dto);
        entity.setId(null);
        BeneficiaryOrgData saved = orgDataRepository.save(entity);
        return orgDataMapper.toDto(saved);
    }

    @Override
    @Transactional
    public BeneficiaryOrgDataDto update(Long id, BeneficiaryOrgDataDto dto) {
        BeneficiaryOrgData existing = orgDataRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("BeneficiaryOrgData not found with id: " + id));
        // apply updates
        existing.setValidFrom(dto.getValidFrom());
        existing.setValidThrough(dto.getValidThrough());
        existing.setPersonnelNumberSystem(dto.getPersonnelNumberSystem());
        existing.setPersonnelNumberPayroll(dto.getPersonnelNumberPayroll());
        existing.setIdentityTag(dto.getIdentityTag());
        existing.setCompanySite(dto.getCompanySite());
        existing.setOrgUnit(dto.getOrgUnit());
        existing.setCostCenter(dto.getCostCenter());
        existing.setUpdatedAt(dto.getUpdatedAt());
        BeneficiaryOrgData updated = orgDataRepository.save(existing);
        return orgDataMapper.toDto(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!orgDataRepository.existsById(id)) {
            throw new NotFoundException("BeneficiaryOrgData not found with id: " + id);
        }
        orgDataRepository.deleteById(id);
    }
}
