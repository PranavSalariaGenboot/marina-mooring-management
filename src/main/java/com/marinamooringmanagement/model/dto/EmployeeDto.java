package com.marinamooringmanagement.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto extends KeyDto implements Serializable {
    private static final long serialVersionUID = 550268063035507976L;
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String phoneNumber;
    private String password;
    private RoleDto role;
}
