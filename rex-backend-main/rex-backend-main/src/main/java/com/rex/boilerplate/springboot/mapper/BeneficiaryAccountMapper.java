package com.rex.boilerplate.springboot.mapper;

import com.rex.boilerplate.springboot.dto.BeneficiaryAccountDto;
import com.rex.boilerplate.springboot.model.BeneficiaryAccount;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import java.util.List;

/**
 * MapStruct mapper aligned to QA DDL for rexbase.beneficiary_account.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface BeneficiaryAccountMapper {

    BeneficiaryAccountMapper INSTANCE = Mappers.getMapper(BeneficiaryAccountMapper.class);

    // Entity -> DTO
    @Mapping(source = "id", target = "id")
    @Mapping(source = "platformId", target = "platformId")
    @Mapping(source = "delegationPermission", target = "delegationPermission")
    @Mapping(source = "keycloakUserid", target = "keycloakUserid")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    BeneficiaryAccountDto toDto(BeneficiaryAccount entity);

    // DTO -> Entity
    @InheritInverseConfiguration
    BeneficiaryAccount toEntity(BeneficiaryAccountDto dto);

    // Collections
    List<BeneficiaryAccountDto> toDtoList(List<BeneficiaryAccount> entities);

    List<BeneficiaryAccount> toEntityList(List<BeneficiaryAccountDto> dtos);
}
