package com.marinamooringmanagement.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto extends KeyDto implements Serializable {

    private static final long serialVersionUID = -3884974621004331133L;

    private String name;
    private String description;
    private Integer id;
}
