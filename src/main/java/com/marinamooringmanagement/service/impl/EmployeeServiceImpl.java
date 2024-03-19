package com.marinamooringmanagement.service.impl;

import com.marinamooringmanagement.dao.EmployeeRepository;
import com.marinamooringmanagement.mapper.EmployeeMapper;
import com.marinamooringmanagement.model.dto.EmployeeDto;
import com.marinamooringmanagement.model.entity.EmployeeEntity;
import com.marinamooringmanagement.model.entity.RoleEntity;
import com.marinamooringmanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper mapper;

    @Override
    public List<EmployeeEntity> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public void saveEmployee(EmployeeDto user) {
        final EmployeeEntity employee = EmployeeEntity.builder().build();

        performSave(user, employee, null);
    }

    @Override
    public void deleteEmployee(Integer empId) {
        employeeRepository.deleteById(empId);
    }

    @Override
    public void updateEmployee(EmployeeDto employeeDto) {
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeDto.getId()).get();
        performSave(employeeDto, employeeEntity, employeeDto.getId());
    }

    @Override
    public EmployeeDto findByEmailAddress(String email) {
        EmployeeDto employee = null;
        if(null != email) {
            EmployeeEntity empEntity = employeeRepository.findByEmail(email).get();
            if(null != empEntity) {
                employee = mapper.mapToUserDto(EmployeeDto.builder().build(), empEntity);
            }
        }
        return employee;
    }

    private void performSave(EmployeeDto user, EmployeeEntity employee, Integer userId) {
        Optional<EmployeeEntity> optionalEmp = employeeRepository.findByEmail("test1@gmail.com");
        EmployeeEntity emp = null;
        if(optionalEmp.isPresent()) {
            System.out.println(optionalEmp.get());
        } else {
            throw new RuntimeException("Email NOT FOUND!!!");
        }
        mapper.mapToUser(employee, user);
        employee.setPassword(passwordEncoder.encode(user.getPassword()));

        RoleEntity role = RoleEntity.builder().build();
        role.setId(1);
        employee.setRole(role);

        employeeRepository.save(employee);
    }
}
