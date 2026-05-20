package com.qualitystream.tutorial.converter;

import com.qualitystream.tutorial.dto.EmployeeDTO;
import com.qualitystream.tutorial.entity.EmployeeEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor // Lombok crea el constructor para hacer injection
public class EmployeeConverter {

    private final ModelMapper modelMapper;

    // Setter/Method Injection, es mejor usar constructor injection
//    @Autowired
//    private void initialize(ModelMapper modelMapper) {
//        this.modelMapper = modelMapper;
//    }

    public EmployeeDTO entityToDto(EmployeeEntity employeeEntity) {
        return modelMapper.map(employeeEntity, EmployeeDTO.class);
    }

    public EmployeeEntity dtoToEntity(EmployeeDTO employeeDTO) {
        return modelMapper.map(employeeDTO, EmployeeEntity.class);
    }

}
