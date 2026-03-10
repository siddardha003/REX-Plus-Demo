package com.rex.boilerplate.springboot.service;

import com.rex.boilerplate.springboot.dto.PlatformDomainDto;
import com.rex.boilerplate.springboot.exceptions.NotFoundException;
import com.rex.boilerplate.springboot.mapper.PlatformDomainMapper;
import com.rex.boilerplate.springboot.model.PlatformDomain;
import com.rex.boilerplate.springboot.repository.PlatformDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlatformDomainServiceImpl implements PlatformDomainService {

    private final PlatformDomainRepository domainRepository;
    private final PlatformDomainMapper domainMapper;

    @Override
    @Transactional(readOnly = true)
    public List<PlatformDomainDto> fetchByPlatformId(Long platformId) {
        List<PlatformDomain> domains = domainRepository.findAll().stream()
                .filter(d -> d.getPlatformId().equals(platformId))
                .collect(Collectors.toList());
        return domains.stream()
                .map(domainMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PlatformDomainDto create(PlatformDomainDto dto) {
        PlatformDomain entity = domainMapper.toEntity(dto);
        entity.setId(null);
        PlatformDomain saved = domainRepository.save(entity);
        return domainMapper.toDto(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!domainRepository.existsById(id)) {
            throw new NotFoundException("PlatformDomain not found with id: " + id);
        }
        domainRepository.deleteById(id);
    }
}
