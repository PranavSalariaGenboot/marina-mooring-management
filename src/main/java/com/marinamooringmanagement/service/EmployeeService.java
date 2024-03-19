package com.marinamooringmanagement.service;


import com.marinamooringmanagement.model.dto.EmployeeDto;
import com.marinamooringmanagement.model.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeService {
    public List<EmployeeEntity> getAllEmployee();

    public void saveEmployee(EmployeeDto employee);

    void deleteEmployee(Integer empId);

    public void updateEmployee(EmployeeDto employee);

    public EmployeeDto findByEmailAddress(String email);
}
