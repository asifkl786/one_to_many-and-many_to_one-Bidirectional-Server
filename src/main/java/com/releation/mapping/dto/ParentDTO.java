package com.releation.mapping.dto;

import java.util.List;

import lombok.Data;

@Data
public class ParentDTO {
    private Long id;
    private String name;
    private Integer age;
    private List<ChildDTO> children;
    
}

