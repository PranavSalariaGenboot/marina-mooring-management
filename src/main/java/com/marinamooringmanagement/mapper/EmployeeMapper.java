package com.marinamooringmanagement.mapper;

import com.marinamooringmanagement.model.dto.EmployeeDto;
import com.marinamooringmanagement.model.entity.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EmployeeMapper {

    EmployeeDto mapToUserDto(@MappingTarget EmployeeDto dto, EmployeeEntity user);

    @Mapping(target = "role", ignore = true)
    void mapToUser(@MappingTarget EmployeeEntity entity, EmployeeDto userDto);
}
