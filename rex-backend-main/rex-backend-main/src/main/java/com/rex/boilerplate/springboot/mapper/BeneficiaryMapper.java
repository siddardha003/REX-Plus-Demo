// BeneficiaryMapper.java
package com.rex.boilerplate.springboot.mapper;

import com.rex.boilerplate.springboot.dto.BeneficiaryDto;
import com.rex.boilerplate.springboot.model.Beneficiary;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface BeneficiaryMapper {

    BeneficiaryMapper INSTANCE = Mappers.getMapper(BeneficiaryMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "beneficiaryId", target = "beneficiaryId")
    @Mapping(source = "commodisId", target = "commodisId")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "academicTitle", target = "academicTitle")
    @Mapping(source = "listName", target = "listName")
    @Mapping(source = "leavingDate", target = "leavingDate")
    @Mapping(source = "createdAt", target = "createdAt")
    @Mapping(source = "updatedAt", target = "updatedAt")
    BeneficiaryDto toDto(Beneficiary entity);

    @InheritInverseConfiguration
    Beneficiary toEntity(BeneficiaryDto dto);

    List<BeneficiaryDto> toDtoList(List<Beneficiary> entities);

    List<Beneficiary> toEntityList(List<BeneficiaryDto> dtos);
}
