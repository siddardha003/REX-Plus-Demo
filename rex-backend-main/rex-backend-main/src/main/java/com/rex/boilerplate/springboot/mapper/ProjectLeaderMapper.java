package com.rex.boilerplate.springboot.mapper;

import com.rex.boilerplate.springboot.dto.ProjectLeaderDto;
import com.rex.boilerplate.springboot.model.ProjectLeader;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectLeaderMapper {
    ProjectLeaderDto toDto(ProjectLeader entity);

    ProjectLeader toEntity(ProjectLeaderDto dto);
}
