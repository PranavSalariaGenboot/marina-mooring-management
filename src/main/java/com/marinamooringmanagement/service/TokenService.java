package com.marinamooringmanagement.service;

import com.marinamooringmanagement.model.dto.EmployeeDto;
import com.marinamooringmanagement.model.entity.TokenEntity;

public interface TokenService {

    void saveToken(final TokenEntity tokenEntity);

    void saveToken(final EmployeeDto emp, final String token);
}
