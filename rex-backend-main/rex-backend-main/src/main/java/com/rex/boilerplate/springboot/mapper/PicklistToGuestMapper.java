package com.rex.boilerplate.springboot.mapper;

import org.mapstruct.Mapper;

import com.rex.boilerplate.springboot.dto.PicklistToGuestDto;
import com.rex.boilerplate.springboot.model.PicklistToGuest;

import java.util.List;

import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PicklistToGuestMapper {

    PicklistToGuestDto toDto(PicklistToGuest entity);

    PicklistToGuest toEntity(PicklistToGuestDto dto);

    List<PicklistToGuestDto> toDtoList(List<PicklistToGuest> entities);

    List<PicklistToGuest> toEntityList(List<PicklistToGuestDto> dtos);
}
