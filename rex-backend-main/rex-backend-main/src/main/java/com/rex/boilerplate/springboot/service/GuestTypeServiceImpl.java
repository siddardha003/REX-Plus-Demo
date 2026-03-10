package com.rex.boilerplate.springboot.service;

import com.rex.boilerplate.springboot.dto.GuestTypeDto;
import com.rex.boilerplate.springboot.exceptions.NotFoundException;
import com.rex.boilerplate.springboot.mapper.GuestTypeMapper;
import com.rex.boilerplate.springboot.model.GuestType;
import com.rex.boilerplate.springboot.repository.GuestTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GuestTypeServiceImpl implements GuestTypeService {

    private final GuestTypeRepository guestTypeRepository;
    private final GuestTypeMapper guestTypeMapper;

    @Override
    @Transactional(readOnly = true)
    public List<GuestTypeDto> fetchAll() {
        return guestTypeRepository.findAll().stream()
                .map(guestTypeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public GuestTypeDto getById(Long id) {
        GuestType entity = guestTypeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("GuestType not found with id: " + id));
        return guestTypeMapper.toDto(entity);
    }

    @Override
    @Transactional
    public GuestTypeDto create(GuestTypeDto dto) {
        GuestType entity = guestTypeMapper.toEntity(dto);
        entity.setId(null);
        GuestType saved = guestTypeRepository.save(entity);
        return guestTypeMapper.toDto(saved);
    }

    @Override
    @Transactional
    public GuestTypeDto update(Long id, GuestTypeDto dto) {
        GuestType existing = guestTypeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("GuestType not found with id: " + id));
        existing.setCode(dto.getCode());
        existing.setDisplayValue(dto.getDisplayValue());
        existing.setActive(dto.getActive());
        GuestType updated = guestTypeRepository.save(existing);
        return guestTypeMapper.toDto(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!guestTypeRepository.existsById(id)) {
            throw new NotFoundException("GuestType not found with id: " + id);
        }
        guestTypeRepository.deleteById(id);
    }
}
