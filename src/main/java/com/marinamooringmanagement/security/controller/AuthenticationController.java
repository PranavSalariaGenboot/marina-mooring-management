package com.marinamooringmanagement.security.controller;

import com.marinamooringmanagement.model.dto.EmployeeDto;
import com.marinamooringmanagement.security.config.JwtUtil;
import com.marinamooringmanagement.security.model.AuthenticationRequest;
import com.marinamooringmanagement.security.model.AuthenticationResponse;
import com.marinamooringmanagement.service.EmployeeService;
import com.marinamooringmanagement.service.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    TokenService tokenService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody AuthenticationRequest authenticationRequest,
            HttpServletRequest request
    ) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );

        return generateAuthenticationResponse(authenticationRequest.getUsername());
    }

    private ResponseEntity<?> generateAuthenticationResponse(String username) {
        final EmployeeDto emp = employeeService.findByEmailAddress(username);
        final String token = jwtUtil.generateToken(emp);
        tokenService.saveToken(emp, token);
        final AuthenticationResponse response = new AuthenticationResponse();
        response.setToken(token);
        response.setUser(emp);
        return ResponseEntity.ok(response);
    }
}
