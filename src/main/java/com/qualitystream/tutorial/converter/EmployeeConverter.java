package com.qualitystream.tutorial.converter;

import com.qualitystream.tutorial.dto.EmployeeDTO;
import com.qualitystream.tutorial.entity.EmployeeEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

@Component
@RequiredArgsConstructor // Lombok generates the constructor for dependency injection
public class EmployeeConverter {

        // EXTERNAL LIBRARY tool for automatic object mapping -> We have to create the Bean manually.
    private final ModelMapper modelMapper;
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    // Converts database Entity to API DTO
    public EmployeeDTO entityToDto(EmployeeEntity employeeEntity) {

        EmployeeDTO returnValue = modelMapper.map(employeeEntity, EmployeeDTO.class);
        Optional<LocalDate> localDateOptional = Optional.ofNullable(employeeEntity.getDateStart());
        if (localDateOptional.isPresent()) {
            LocalDate localDate = localDateOptional.get();
            returnValue.setDateStart(dateTimeFormatter.format(localDate));
        }

        return returnValue;
    }

    // Converts API DTO to database Entity
    public EmployeeEntity dtoToEntity(EmployeeDTO employeeDTO) {
        return modelMapper.map(employeeDTO, EmployeeEntity.class);
    }



}