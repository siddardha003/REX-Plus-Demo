package com.rex.boilerplate.springboot.mapper;

import com.rex.boilerplate.springboot.dto.ProjectNumberDto;
import com.rex.boilerplate.springboot.model.ProjectNumber;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectNumberMapper {
    ProjectNumberDto toDto(ProjectNumber entity);

    ProjectNumber toEntity(ProjectNumberDto dto);
}
