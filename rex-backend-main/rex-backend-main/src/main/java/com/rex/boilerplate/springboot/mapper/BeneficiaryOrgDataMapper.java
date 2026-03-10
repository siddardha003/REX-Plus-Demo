package com.rex.boilerplate.springboot.mapper;

import com.rex.boilerplate.springboot.dto.BeneficiaryOrgDataDto;
import com.rex.boilerplate.springboot.model.BeneficiaryOrgData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;

/**
 * MapStruct mapper for BeneficiaryOrgData ↔ BeneficiaryOrgDataDto
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BeneficiaryOrgDataMapper {

    BeneficiaryOrgDataMapper INSTANCE = Mappers.getMapper(BeneficiaryOrgDataMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "beneficiaryAccountId", target = "beneficiaryAccountId")
    @Mapping(source = "employerId", target = "employerId")
    @Mapping(source = "validFrom", target = "validFrom")
    @Mapping(source = "validThrough", target = "validThrough")
    @Mapping(source = "personnelNumberSystem", target = "personnelNumberSystem")
    @Mapping(source = "personnelNumberPayroll", target = "personnelNumberPayroll")
    @Mapping(source = "identityTag", target = "identityTag")
    @Mapping(source = "companySite", target = "companySite")
    @Mapping(source = "orgUnit", target = "orgUnit")
    @Mapping(source = "costCenter", target = "costCenter")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    BeneficiaryOrgDataDto toDto(BeneficiaryOrgData entity);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "beneficiaryAccountId", target = "beneficiaryAccountId")
    @Mapping(source = "employerId", target = "employerId")
    @Mapping(source = "validFrom", target = "validFrom")
    @Mapping(source = "validThrough", target = "validThrough")
    @Mapping(source = "personnelNumberSystem", target = "personnelNumberSystem")
    @Mapping(source = "personnelNumberPayroll", target = "personnelNumberPayroll")
    @Mapping(source = "identityTag", target = "identityTag")
    @Mapping(source = "companySite", target = "companySite")
    @Mapping(source = "orgUnit", target = "orgUnit")
    @Mapping(source = "costCenter", target = "costCenter")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    BeneficiaryOrgData toEntity(BeneficiaryOrgDataDto dto);

    List<BeneficiaryOrgDataDto> toDtoList(List<BeneficiaryOrgData> entities);

    List<BeneficiaryOrgData> toEntityList(List<BeneficiaryOrgDataDto> dtos);
}
