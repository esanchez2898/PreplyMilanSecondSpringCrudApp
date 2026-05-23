package com.qualitystream.tutorial.converter;

import com.qualitystream.tutorial.dto.EmployeeDTO;
import com.qualitystream.tutorial.entity.EmployeeEntity;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
@RequiredArgsConstructor // Lombok auto-generates a constructor injecting all 'final' fields
public class EmployeeConverter {

    // ModelMapper automatically maps fields with the same name between two objects (e.g. Entity <-> DTO)
    // It must be declared as a Spring Bean manually in a @Configuration class
    private final ModelMapper modelMapper;

    // Defines the date format pattern we expect: year-month-day (e.g. 2024-03-15)
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * Converts database Entity ---> API DTO.
     * The date field changes from LocalDate (Java object) to String (JSON-friendly).
     */
    public EmployeeDTO entityToDto(EmployeeEntity employeeEntity) {

        // Automatically maps all fields with matching names from Entity to DTO
        EmployeeDTO returnValue = modelMapper.map(employeeEntity, EmployeeDTO.class);   // 95% match

        Optional<LocalDate> dateStartOptional = Optional.ofNullable(employeeEntity.getDateStart());

        if (dateStartOptional.isPresent()) {
            LocalDate dateStart = dateStartOptional.get();                        // Extract the LocalDate from the Optional box
            returnValue.setDateStart(dateTimeFormatter.format(dateStart));        // the other 5%          // Convert LocalDate -> String (e.g. "2024-03-15")
        }

        return returnValue; // 100%
    }

    /**
     * Converts API DTO ---> database Entity.
     * The date field changes from String (JSON-friendly) to LocalDate (Java object).
     */
    public EmployeeEntity dtoToEntity(EmployeeDTO employeeDTO) {

        // Automatically maps all fields with matching names from DTO to Entity
        EmployeeEntity returnValue = modelMapper.map(employeeDTO, EmployeeEntity.class);    // 95%

        Optional<String> dateStartOptional = Optional.ofNullable(employeeDTO.getDateStart());

        if (dateStartOptional.isPresent()) {
            String dateStartStr = dateStartOptional.get();                        // Extract the String from the Optional box
            LocalDate dateStart = LocalDate.parse(dateStartStr, dateTimeFormatter); // Convert String -> LocalDate (e.g. "2024-03-15" -> LocalDate object)
            returnValue.setDateStart(dateStart);        // The other 5%
        }

        return returnValue;     // 100%
    }
}