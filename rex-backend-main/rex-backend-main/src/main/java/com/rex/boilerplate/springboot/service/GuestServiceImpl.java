package com.rex.boilerplate.springboot.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rex.boilerplate.springboot.dto.GuestDto;
import com.rex.boilerplate.springboot.mapper.GuestMapper;
import com.rex.boilerplate.springboot.model.Guest;
import com.rex.boilerplate.springboot.repository.GuestRepository;

@Service
public class GuestServiceImpl {

    private final GuestRepository repository;
    private final GuestMapper mapper;

    public GuestServiceImpl(GuestRepository repository, GuestMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public GuestDto create(GuestDto dto) {
        // Ignore id, createdAt, updatedAt, listname (generated) — mapper handles ignores on toEntity
        Guest entity = mapper.toEntity(dto);
        Guest saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Transactional(readOnly = true)
    public GuestDto get(Long id) {
        Guest entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Guest not found: id=" + id));
        return mapper.toDto(entity);
    }

    @Transactional
    public GuestDto update(Long id, GuestDto dto) {
        Guest entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Guest not found: id=" + id));

        // PATCH semantics: ignore nulls in dto
        mapper.updateFromDto(dto, entity);

        // listname is generated; do not touch it
        Guest saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Page<GuestDto> list(Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDto);
    }
}
