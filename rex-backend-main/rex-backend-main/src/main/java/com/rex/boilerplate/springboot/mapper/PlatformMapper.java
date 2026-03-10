package com.rex.boilerplate.springboot.mapper;

import com.rex.boilerplate.springboot.dto.PlatformDto;
import com.rex.boilerplate.springboot.model.Platform;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import java.util.List;

/**
 * MapStruct mapper for Platform ↔ PlatformDto
 */
@Mapper(componentModel = "spring")
public interface PlatformMapper {

    PlatformMapper INSTANCE = Mappers.getMapper(PlatformMapper.class);

    PlatformDto toDto(Platform entity);

    @Mapping(target = "activePeriod", ignore = true)
    Platform toEntity(PlatformDto dto);

    List<PlatformDto> toDtoList(List<Platform> entities);

    List<Platform> toEntityList(List<PlatformDto> dtos);
}
