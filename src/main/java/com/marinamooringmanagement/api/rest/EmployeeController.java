package com.marinamooringmanagement.api.rest;

import com.marinamooringmanagement.model.dto.EmployeeDto;
import com.marinamooringmanagement.model.entity.EmployeeEntity;
import com.marinamooringmanagement.response.BasicRestResponse;
import com.marinamooringmanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "v1/mmm/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(
            value = "/getAllEmployees",
            method = RequestMethod.GET,
            produces = {"application/json", "application/xml"}
    )
    @ResponseStatus(HttpStatus.OK)
    public BasicRestResponse getAllEmployees() {
        List<EmployeeEntity> employeesList = employeeService.getAllEmployee();
        final BasicRestResponse response = BasicRestResponse.builder().build();
        response.setContent(employeesList);
        response.setMessage("Employee List");
        response.setStatus(HttpStatus.OK.value());
        return response;
    }


    @RequestMapping(
            value = "/saveEmployee",
            method = RequestMethod.POST,
            produces = {"application/json", "application/xml"}
    )
    @ResponseStatus(HttpStatus.OK)
    public
    BasicRestResponse saveEmployee(
            @RequestBody EmployeeDto employee
    ) {
        final BasicRestResponse response = new BasicRestResponse();
        employeeService.saveEmployee(employee);
        response.setMessage("Employee Saved Successfully!!!");
        response.setStatus(HttpStatus.OK.value());
        return response;
    }

    @RequestMapping(
            value = "/updateEmployee",
            method = RequestMethod.PUT,
            produces = {"application/json", "application/xml"}
    )
    @ResponseStatus(HttpStatus.OK)
    public
    BasicRestResponse updateEmployee(
            @RequestBody EmployeeDto employee
    ) {
        final BasicRestResponse response = new BasicRestResponse();
        employeeService.updateEmployee(employee);
        response.setMessage("Employee Saved Successfully!!!");
        response.setStatus(HttpStatus.OK.value());
        return response;
    }

    @RequestMapping(
            value = "/deleteEmployee/{empId}",
            method = RequestMethod.DELETE,
            produces = {"application/json", "application/xml"}
    )
    @ResponseStatus(HttpStatus.OK)
    public
    BasicRestResponse deleteEmployee(
            @PathVariable("empId") Integer empId
    ) {
        final BasicRestResponse response = new BasicRestResponse();
        employeeService.deleteEmployee(empId);
        response.setMessage("Employee Deleted Successfully!!!");
        response.setStatus(HttpStatus.OK.value());
        return response;
    }
}
