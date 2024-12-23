package com.releation.mapping.service;

import java.time.LocalDate;
import java.util.List;

import com.releation.mapping.dto.ChildDTO;
import com.releation.mapping.dto.ParentDTO;

public interface ParentService {
	
	ParentDTO addParent(ParentDTO parentDTO);
	ParentDTO getParentById(Long id);
	List<ParentDTO> getAllParent();
	ParentDTO updateParent(Long id, ParentDTO parentDTO);
	void deleteParent(Long id);
	List<ParentDTO> searchParent(String name, Integer age);
	
	/* This Section For Children */
	List<ChildDTO> searchChild(String name, Integer age, String gender, LocalDate dateOfBirth, Long parentId);
	void deleteChild(Long id);
	ChildDTO getChildById(Long id);
	List<ChildDTO> getAllChild();
	ParentDTO addChild(ParentDTO parentDTO);
	ChildDTO addChildWithChildDTO(ChildDTO childDTO);
	

}
