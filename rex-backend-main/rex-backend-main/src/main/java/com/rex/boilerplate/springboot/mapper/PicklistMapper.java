package com.rex.boilerplate.springboot.mapper;

import com.rex.boilerplate.springboot.dto.PicklistDto;
import com.rex.boilerplate.springboot.model.Picklist;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;

/**
 * MapStruct mapper for Picklist ↔ PicklistDto
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PicklistMapper {

    PicklistMapper INSTANCE = Mappers.getMapper(PicklistMapper.class);

    @Mapping(source = "beneficiaryAccountId", target = "beneficiaryAccountId")
    @Mapping(source = "displayName", target = "displayname")
    @Mapping(source = "sortId", target = "sortId")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    PicklistDto toDto(Picklist entity);

    @Mapping(source = "beneficiaryAccountId", target = "beneficiaryAccountId")
    @Mapping(source = "displayname", target = "displayName")
    @Mapping(source = "sortId", target = "sortId")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    Picklist toEntity(PicklistDto dto);

    List<PicklistDto> toDtoList(List<Picklist> entities);

    List<Picklist> toEntityList(List<PicklistDto> dtos);
}
