package com.rex.boilerplate.springboot.mapper;

import com.rex.boilerplate.springboot.dto.GuestTypeDto;
import com.rex.boilerplate.springboot.model.GuestType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import java.util.List;

/**
 * MapStruct mapper for GuestType ↔ GuestTypeDto
 */
@Mapper(componentModel = "spring")
public interface GuestTypeMapper {

    GuestTypeMapper INSTANCE = Mappers.getMapper(GuestTypeMapper.class);

    @Mapping(source = "code",         target = "code")
    @Mapping(source = "displayValue", target = "displayValue")
    @Mapping(source = "active",       target = "active")
    GuestTypeDto toDto(GuestType entity);

    @Mapping(source = "code",         target = "code")
    @Mapping(source = "displayValue", target = "displayValue")
    @Mapping(source = "active",       target = "active")
    GuestType toEntity(GuestTypeDto dto);

    List<GuestTypeDto> toDtoList(List<GuestType> entities);
    List<GuestType> toEntityList(List<GuestTypeDto> dtos);
}
