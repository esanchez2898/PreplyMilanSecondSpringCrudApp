package com.qualitystream.tutorial.converter;

import com.qualitystream.tutorial.dto.EmployeeDTO;
import com.qualitystream.tutorial.entity.EmployeeEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor // Lombok automatically generates the constructor for dependency injection
public class EmployeeConverter {

    private final ModelMapper modelMapper;

    // Example of Setter/Method Injection.
    // Constructor Injection is generally recommended because:
    // - Dependencies are required at object creation time
    // - It improves immutability
    // - It makes testing easier
//    @Autowired
//    private void initialize(ModelMapper modelMapper) {
//        this.modelMapper = modelMapper;
//    }

    // Converts Entity -> DTO
    public EmployeeDTO entityToDto(EmployeeEntity employeeEntity) {
        return modelMapper.map(employeeEntity, EmployeeDTO.class);
    }

    // Converts DTO -> Entity
    public EmployeeEntity dtoToEntity(EmployeeDTO employeeDTO) {
        return modelMapper.map(employeeDTO, EmployeeEntity.class);
    }

}