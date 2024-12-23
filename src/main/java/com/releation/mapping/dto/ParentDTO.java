package com.releation.mapping.dto;



import java.util.List;


public class ParentDTO {
    private Long id;
    private String name;
    private Integer age;
    private List<ChildDTO> children;
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public List<ChildDTO> getChildren() {
		return children;
	}
	public void setChildren(List<ChildDTO> children) {
		this.children = children;
	}
    
    
}

