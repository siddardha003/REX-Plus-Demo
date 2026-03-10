package com.rex.boilerplate.springboot.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.rex.boilerplate.springboot.dto.GuestDto;
import com.rex.boilerplate.springboot.model.Guest;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GuestMapper {

    // ENTITY -> DTO
    @Mapping(source = "defaultGuestType.id", target = "defaultGuesttypeId")
    GuestDto toDto(Guest entity);

    List<GuestDto> toDto(List<Guest> entities);

    // DTO -> ENTITY (for CREATE). DB manages id, listname, created/updated
    // timestamps.
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "listname", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(source = "defaultGuesttypeId", target = "defaultGuestType.id")
    Guest toEntity(GuestDto dto);

    // Partial UPDATE (PATCH semantics) — ignore nulls so we don't overwrite
    // existing values.
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "listname", ignore = true) // stored/generated column
    @Mapping(target = "createdAt", ignore = true) // DB-managed
    @Mapping(target = "updatedAt", ignore = true) // DB-managed
    @Mapping(source = "defaultGuesttypeId", target = "defaultGuestType.id")
    void updateFromDto(GuestDto dto, @MappingTarget Guest entity);
}
