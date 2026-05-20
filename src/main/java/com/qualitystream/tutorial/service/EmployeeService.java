package com.qualitystream.tutorial.service;

import com.qualitystream.tutorial.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDTO> getAllEmployees();
    EmployeeDTO getById(Integer employeeId);
    EmployeeDTO addEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO updateEmployee(Integer employeeId, EmployeeDTO employeeDTO);
    void deleteEmployee(Integer employeeId);


}
