package com.marinamooringmanagement.security.model;

import com.marinamooringmanagement.model.dto.EmployeeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse implements Serializable {

    private static final long serialVersionUID = 550269063035507976L;

    private String token;
    private EmployeeDto user;
}
