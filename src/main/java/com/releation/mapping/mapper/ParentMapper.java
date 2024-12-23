package com.releation.mapping.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.releation.mapping.dto.ChildDTO;
import com.releation.mapping.dto.ParentDTO;
import com.releation.mapping.entity.Child;
import com.releation.mapping.entity.Parent;

@Component
public class ParentMapper {
	
	
	// Convert to Entity
	public Parent toParentEntity(ParentDTO parentDTO) {
		Parent parent = new Parent();
		parent.setId(parentDTO.getId());
		parent.setName(parentDTO.getName());
		parent.setAge(parentDTO.getAge());
	//	parent.setChildren(parentDTO.getChildren()); Ye Set Nahi Ho raha h
		
		if(parentDTO.getChildren() != null) {
			List<Child> childs = parentDTO.getChildren().stream().map(this::toChildEntity).collect(Collectors.toList());
			parent.setChildren(childs);
			
			 // Set the parent in each child
            childs.forEach(child -> child.setParent(parent));
		}
		return parent;	
	}
	
	// Convert to DTO
	public ParentDTO toParentDTO(Parent parent) {
		ParentDTO parentDTO = new ParentDTO();
		parentDTO.setId(parent.getId());
		parentDTO.setName(parent.getName());
		parentDTO.setAge(parent.getAge());
		parentDTO.setChildren(parent.getChildren()
				      .stream()
				      .map(this::toChildDTO)
				      .collect(Collectors.toList()));
		return parentDTO;
	}
	
	
	// Convert to Child DTO 
	 public ChildDTO toChildDTO(Child child) {
	        ChildDTO dto = new ChildDTO();
	        dto.setId(child.getId());
	        dto.setName(child.getName());
	        dto.setAge(child.getAge());
	        dto.setGender(child.getGender());
	        dto.setDateOfBirth(child.getDateOfBirth());
	        return dto;
	    }
	// Convert to Child Entity
	public Child toChildEntity(ChildDTO childDTO) {
        Child child = new Child();
        child.setId(childDTO.getId());
        child.setName(childDTO.getName());
        child.setAge(childDTO.getAge());
        child.setDateOfBirth(childDTO.getDateOfBirth());
        child.setGender(childDTO.getGender());
        return child;
    }

}
