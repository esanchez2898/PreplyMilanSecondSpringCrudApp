package com.qualitystream.tutorial.controller;

import com.qualitystream.tutorial.dto.EmployeeDTO;
import com.qualitystream.tutorial.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/employees")

public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EmployeeDTO> getById(@PathVariable("id") Integer employeeId) {
        return new ResponseEntity<>(employeeService.getById(employeeId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO employeeDtoSaved = employeeService.addEmployee(employeeDTO);
        return new ResponseEntity<>("Employee with Id: " + employeeDtoSaved.getId() + " was created.", HttpStatus.CREATED);
    }


}
