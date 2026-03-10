package com.rex.boilerplate.springboot.mapper;

import com.rex.boilerplate.springboot.dto.PlatformDomainDto;
import com.rex.boilerplate.springboot.model.PlatformDomain;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;

/**
 * MapStruct mapper for PlatformDomain ↔ PlatformDomainDto
 */
@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface PlatformDomainMapper {

    PlatformDomainMapper INSTANCE = Mappers.getMapper(PlatformDomainMapper.class);

    @Mapping(source = "platformId", target = "platformId")
    @Mapping(source = "domainName", target = "domainName")
    PlatformDomainDto toDto(PlatformDomain entity);

    @Mapping(source = "platformId", target = "platformId")
    @Mapping(source = "domainName", target = "domainName")
    PlatformDomain toEntity(PlatformDomainDto dto);

    List<PlatformDomainDto> toDtoList(List<PlatformDomain> entities);
    List<PlatformDomain> toEntityList(List<PlatformDomainDto> dtos);
}
