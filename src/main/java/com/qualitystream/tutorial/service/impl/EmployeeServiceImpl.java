package com.qualitystream.tutorial.service.impl;

import com.qualitystream.tutorial.converter.EmployeeConverter;
import com.qualitystream.tutorial.dto.EmployeeDTO;
import com.qualitystream.tutorial.entity.EmployeeEntity;
import com.qualitystream.tutorial.exception.EmployeeAlreadyExistException;
import com.qualitystream.tutorial.exception.EmployeeNotFoundException;
import com.qualitystream.tutorial.repository.EmployeeRepository;
import com.qualitystream.tutorial.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeConverter employeeConverter;

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeDTO> getAllEmployees() {

        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();

        for (EmployeeEntity e : employeeEntityList) {
            employeeDTOList.add(employeeConverter.entityToDto(e));
        }
        return employeeDTOList;
    }

    @Override
    public EmployeeDTO getById(Integer employeeId) {
        return employeeConverter.entityToDto(
                employeeRepository.findById(employeeId).orElseThrow(
                        () -> new EmployeeNotFoundException("The employee with id " + employeeId + " has not been found")));
    }

    @Override
    @Transactional
    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {

        if (employeeRepository.findByEmail(employeeDTO.getEmail()).isPresent()) {
            throw new EmployeeAlreadyExistException("The employee Already Exist");
        }
        EmployeeEntity employeeEntitySaved = employeeRepository.save(employeeConverter.dtoToEntity(employeeDTO));
        return employeeConverter.entityToDto(employeeEntitySaved);
    }

    @Override
    @Transactional
    public EmployeeDTO updateEmployee(Integer employeeId, EmployeeDTO employeeDTO) {

        EmployeeEntity existingEmployee =
                employeeRepository.findById(employeeId)
                        .orElseThrow(() -> new EmployeeNotFoundException("The employee with id " + employeeId + " has not been found"));
        employeeDTO.setId(employeeId);

        EmployeeEntity employeeEntity = employeeRepository.save(employeeConverter.dtoToEntity(employeeDTO));
        return employeeConverter.entityToDto(employeeEntity);
    }

    @Override
    @Transactional
    public void deleteEmployee(Integer employeeId) {
        getById(employeeId);
        employeeRepository.deleteById(employeeId);
    }
}
