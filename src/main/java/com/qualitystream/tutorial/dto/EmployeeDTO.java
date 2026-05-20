package com.qualitystream.tutorial.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO implements Serializable {

    private Integer id;

    @Size(max = 50, min = 5)
    @NotEmpty
    private String firstname;

    @Size(max = 50, min = 5)
    @NotEmpty
    private String lastname;

    @Email(regexp = ".+[@].+[\\.].+")
    @NotEmpty
    @Size(max = 50)
    private String email;

    @NotEmpty
    private String dateStart;

    @NotNull
    private Double salary;

    @Size(max = 100, min = 5)
    @NotEmpty
    private String position;

    @Size(max = 100, min = 5)
    @NotEmpty
    private String location;

}
