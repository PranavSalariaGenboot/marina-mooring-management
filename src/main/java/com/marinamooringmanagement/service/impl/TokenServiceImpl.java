package com.marinamooringmanagement.service.impl;

import com.marinamooringmanagement.dao.TokenRepository;
import com.marinamooringmanagement.exception.DBOperationException;
import com.marinamooringmanagement.model.dto.EmployeeDto;
import com.marinamooringmanagement.model.entity.EmployeeEntity;
import com.marinamooringmanagement.model.entity.TokenEntity;
import com.marinamooringmanagement.security.config.JwtUtil;
import com.marinamooringmanagement.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void saveToken(TokenEntity tokenEntity) {
        try {
            if(null != tokenEntity) {
                tokenRepository.save(tokenEntity);
            }
        } catch (Exception e) {
            throw new DBOperationException(e.getMessage(), e);
        }
    }

    @Override
    public void saveToken(final EmployeeDto emp, final String token) {
        try {
            if(null != emp && StringUtils.hasText(token)) {
                final EmployeeEntity employeeEntity = EmployeeEntity.builder().build();
                employeeEntity.setId(emp.getId());
                final Date expireDateTime = jwtUtil.getExpireTimeFromToken(token);
                final TokenEntity tokenEntity = TokenEntity.builder()
                        .employee(employeeEntity)
                        .token(token)
                        .expireAt(expireDateTime)
                        .build();
                saveToken(tokenEntity);
            }
        } catch (Exception e) {

        }
    }
}
