package com.marinamooringmanagement.mapper;

import com.marinamooringmanagement.model.dto.EmployeeDto;
import com.marinamooringmanagement.model.dto.RoleDto;
import com.marinamooringmanagement.model.entity.EmployeeEntity;
import com.marinamooringmanagement.model.entity.RoleEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-19T16:26:33+0530",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 20.0.1 (Oracle Corporation)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public EmployeeDto mapToUserDto(EmployeeDto dto, EmployeeEntity user) {
        if ( user == null ) {
            return dto;
        }

        if ( user.getId() != null ) {
            dto.setId( user.getId() );
        }
        if ( user.getFirstname() != null ) {
            dto.setFirstname( user.getFirstname() );
        }
        if ( user.getLastname() != null ) {
            dto.setLastname( user.getLastname() );
        }
        if ( user.getEmail() != null ) {
            dto.setEmail( user.getEmail() );
        }
        if ( user.getPhoneNumber() != null ) {
            dto.setPhoneNumber( user.getPhoneNumber() );
        }
        if ( user.getPassword() != null ) {
            dto.setPassword( user.getPassword() );
        }
        if ( user.getRole() != null ) {
            if ( dto.getRole() == null ) {
                dto.setRole( RoleDto.builder().build() );
            }
            roleEntityToRoleDto( user.getRole(), dto.getRole() );
        }

        return dto;
    }

    @Override
    public void mapToUser(EmployeeEntity entity, EmployeeDto userDto) {
        if ( userDto == null ) {
            return;
        }

        if ( userDto.getId() != null ) {
            entity.setId( userDto.getId() );
        }
        if ( userDto.getFirstname() != null ) {
            entity.setFirstname( userDto.getFirstname() );
        }
        if ( userDto.getLastname() != null ) {
            entity.setLastname( userDto.getLastname() );
        }
        if ( userDto.getEmail() != null ) {
            entity.setEmail( userDto.getEmail() );
        }
        if ( userDto.getPhoneNumber() != null ) {
            entity.setPhoneNumber( userDto.getPhoneNumber() );
        }
        if ( userDto.getPassword() != null ) {
            entity.setPassword( userDto.getPassword() );
        }
    }

    protected void roleEntityToRoleDto(RoleEntity roleEntity, RoleDto mappingTarget) {
        if ( roleEntity == null ) {
            return;
        }

        if ( roleEntity.getCreationDate() != null ) {
            mappingTarget.setCreationDate( roleEntity.getCreationDate() );
        }
        if ( roleEntity.getName() != null ) {
            mappingTarget.setName( roleEntity.getName() );
        }
        if ( roleEntity.getDescription() != null ) {
            mappingTarget.setDescription( roleEntity.getDescription() );
        }
        if ( roleEntity.getId() != null ) {
            mappingTarget.setId( roleEntity.getId() );
        }
    }
}
