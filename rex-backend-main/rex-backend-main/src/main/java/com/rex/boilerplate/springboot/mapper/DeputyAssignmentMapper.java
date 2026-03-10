package com.rex.boilerplate.springboot.mapper;

import com.rex.boilerplate.springboot.dto.DeputyAssignmentDto;
import com.rex.boilerplate.springboot.model.DeputyAssignment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;

/**
 * MapStruct mapper for DeputyAssignment ↔ DeputyAssignmentDto
 */
@Mapper(
    componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface DeputyAssignmentMapper {

    DeputyAssignmentMapper INSTANCE = Mappers.getMapper(DeputyAssignmentMapper.class);

    @Mapping(source = "validFrom",             target = "validFrom")
    @Mapping(source = "validThrough",          target = "validThrough")
    @Mapping(source = "beneficiaryAccountId",  target = "beneficiaryAccountId")
    @Mapping(source = "deputyId",              target = "deputyId")
    @Mapping(source = "createdAt",             target = "createdAt")
    @Mapping(source = "updatedAt",             target = "updatedAt")
    DeputyAssignmentDto toDto(DeputyAssignment entity);

    @Mapping(source = "validFrom",             target = "validFrom")
    @Mapping(source = "validThrough",          target = "validThrough")
    @Mapping(source = "beneficiaryAccountId",  target = "beneficiaryAccountId")
    @Mapping(source = "deputyId",              target = "deputyId")
    @Mapping(source = "createdAt",             target = "createdAt")
    @Mapping(source = "updatedAt",             target = "updatedAt")
    DeputyAssignment toEntity(DeputyAssignmentDto dto);

    List<DeputyAssignmentDto> toDtoList(List<DeputyAssignment> entities);
    List<DeputyAssignment> toEntityList(List<DeputyAssignmentDto> dtos);
}
