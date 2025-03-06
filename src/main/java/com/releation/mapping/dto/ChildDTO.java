package com.releation.mapping.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ChildDTO {
	private Long id;
    private String name;
    private Integer age;
    private String gender;
    private LocalDate dateOfBirth;
  
}

