package com.rex.boilerplate.springboot.service;

import com.rex.boilerplate.springboot.dto.PlatformDto;
import com.rex.boilerplate.springboot.exceptions.NotFoundException;
import com.rex.boilerplate.springboot.mapper.PlatformMapper;
import com.rex.boilerplate.springboot.model.Platform;
import com.rex.boilerplate.springboot.repository.PlatformRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlatformServiceImpl implements PlatformService {

    private final PlatformRepository platformRepository;
    private final PlatformMapper platformMapper;

    @Override
    @Transactional(readOnly = true)
    public List<PlatformDto> fetchAll() {
        return platformRepository.findAll().stream()
                .map(platformMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PlatformDto getById(Long id) {
        Platform platform = platformRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Platform not found with id: " + id));
        return platformMapper.toDto(platform);
    }

    @Override
    @Transactional
    public PlatformDto create(PlatformDto dto) {
        Platform entity = platformMapper.toEntity(dto);
        // Ensure ID is null for creation
        entity.setId(null);
        Platform saved = platformRepository.save(entity);
        return platformMapper.toDto(saved);
    }

    @Override
    @Transactional
    public PlatformDto update(Long id, PlatformDto dto) {

        Platform existing = platformRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Platform not found with id: " + id));

        existing.setPlatformUuid(dto.getPlatformUuid());
        existing.setDisplayName(dto.getDisplayName());
        existing.setActive(dto.getActive());
        existing.setUpdatedAt(dto.getUpdatedAt());

        Platform updated = platformRepository.save(existing);

        return platformMapper.toDto(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!platformRepository.existsById(id)) {
            throw new NotFoundException("Platform not found with id: " + id);
        }
        platformRepository.deleteById(id);
    }
}
