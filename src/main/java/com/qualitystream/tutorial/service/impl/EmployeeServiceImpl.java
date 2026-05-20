package com.qualitystream.tutorial.service.impl;

import com.qualitystream.tutorial.converter.EmployeeConverter;
import com.qualitystream.tutorial.dto.EmployeeDTO;
import com.qualitystream.tutorial.entity.EmployeeEntity;
import com.qualitystream.tutorial.repository.EmployeeRepository;
import com.qualitystream.tutorial.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeConverter employeeConverter;

    @Override
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
                        () -> new RuntimeException("")));
    }

    @Override
    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {

//        if (employeeRepository.findById(employeeDTO.getId()).isPresent()) {
//            throw new RuntimeException("Ya existe bro :v");
//        }

        EmployeeEntity employeeEntitySaved = employeeRepository.save(employeeConverter.dtoToEntity(employeeDTO));
        return employeeConverter.entityToDto(employeeEntitySaved);
    }

    @Override
    public EmployeeDTO updateEmployee(Integer employeeId, EmployeeDTO employeeDTO) {

        EmployeeEntity existingEmployee =
                employeeRepository.findById(employeeId)
                        .orElseThrow(() -> new RuntimeException("testing "));
        employeeDTO.setId(employeeId);

        EmployeeEntity employeeEntity = employeeRepository.save(employeeConverter.dtoToEntity(employeeDTO));
        return employeeConverter.entityToDto(employeeEntity);
    }

    @Override
    public void deleteEmployee(Integer employeeId) {

        if (employeeRepository.findById(employeeId).isEmpty()) {
            throw new RuntimeException("No existe ese employee bro");
        }

        employeeRepository.deleteById(employeeId);

    }
}
