package com.qualitystream.tutorial.controller;

import com.qualitystream.tutorial.dto.EmployeeDTO;
import com.qualitystream.tutorial.exception.ValidationFailedException;
import com.qualitystream.tutorial.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
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
    public ResponseEntity<String> addEmployee(@Validated @RequestBody EmployeeDTO employeeDTO, Errors errors) {
        if (errors.hasErrors()) {
            throw new ValidationFailedException("Employee has not been validated");
        }
        EmployeeDTO employeeDtoSaved = employeeService.addEmployee(employeeDTO);
        return new ResponseEntity<>("Employee with Id: " + employeeDtoSaved.getId() + " was created.", HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") Integer employeeId) {
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>("Employee with id: " + employeeId + " was deleted", HttpStatus.OK);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateEmployeeById(@PathVariable("id") Integer employeeId, @Validated @RequestBody EmployeeDTO employeeDTO, Errors errors) {
        if (errors.hasErrors()) {
            throw new ValidationFailedException("Employee has not been validated");
        }

        EmployeeDTO employeeDtoSaved = employeeService.updateEmployee(employeeId, employeeDTO);
        return new ResponseEntity<>("Employee with Id: " + employeeDtoSaved.getId() + " was updated.", HttpStatus.CREATED);
    }

    @GetMapping(value = "/timezones")
    public ResponseEntity<List<String>> getTimezones() {

        List<String> timezones = new ArrayList<>(ZoneId.getAvailableZoneIds());
        Collections.sort(timezones);

        return new ResponseEntity<>(timezones, HttpStatus.OK);

    }

}
