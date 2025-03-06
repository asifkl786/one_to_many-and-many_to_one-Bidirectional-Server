package com.releation.mapping.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.releation.mapping.dto.ChildDTO;
import com.releation.mapping.dto.ParentDTO;
import com.releation.mapping.entity.Child;
import com.releation.mapping.entity.Parent;
import com.releation.mapping.exception.ResourceNotFoundException;
import com.releation.mapping.mapper.ParentMapper;
import com.releation.mapping.repository.ChildRepository;
import com.releation.mapping.repository.ParentRepository;


@Service
public class ParentServiceImple implements ParentService {
	
	
	private static final Logger logger = LoggerFactory.getLogger(ParentServiceImple.class);
	
	
	@Autowired
	ParentRepository parentRepository;
	@Autowired
	ParentMapper parentMapper;
	@Autowired
	ChildRepository childRepository;

	// Add Parent 
	@Override
	public ParentDTO addParent(ParentDTO parentDTO) {
		logger.info("Adding Parent with Name.... :: {}",parentDTO.getName());
		Parent parent = parentMapper.toParentEntity(parentDTO);
		Parent savedParent = parentRepository.save(parent);
		logger.info("{} :: Parent Successfully Add",savedParent.getName());
		return parentMapper.toParentDTO(savedParent);
	}

	// Get Parent
	@Override
	public ParentDTO getParentById(Long id) {
		logger.info("Get Parent By Id With Id: {}",id);	
		Parent parent = parentRepository.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("User is not exists with given id :"  + id));
		logger.info(" Parent Successfully Found With Name :: {} ",parent.getName());
		return parentMapper.toParentDTO(parent);
	}

	
	// Get All Parent
	@Override
	public List<ParentDTO> getAllParent() {
		logger.info("Get All Parent ");
		List<Parent> parents = parentRepository.findAll();
		logger.info("{} :: Parents are Successfully Found ",parents.size());
		return parents.stream().map(parentMapper::toParentDTO).collect(Collectors.toList());
	}

	
	// Update the Parent with Child
	@Override
	public ParentDTO updateParent(Long id, ParentDTO parentDTO) {
		logger.info(" Update Parent With Name :: {}",parentDTO.getName());
		
		// Retrieve the existing parent from the database
        Parent existingParent = parentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Parent not found with ID: " + id));

        // Update parent fields
        existingParent.setName(parentDTO.getName());
        existingParent.setAge(parentDTO.getAge());
       // existingParent.setChildren(parentDTO.getChildren()); ye nahi set ho raha

        // Manage children updates
        if (parentDTO.getChildren() != null) {
            // Clear the current children list to prevent duplicates
           // existingParent.getChildren().clear();

            // Loop through the incoming children
            parentDTO.getChildren().forEach(childDTO -> {
                Child child = parentMapper.toChildEntity(childDTO);

                // Check if the child already exists
                if (childDTO.getId() != null) {
                    // Update the existing child
                    child = existingParent.getChildren().stream()
                            .filter(existingChild -> existingChild.getId().equals(childDTO.getId()))
                            .findFirst()
                            .orElse(child);
                }

                // Set parent for the child
                child.setParent(existingParent);
                existingParent.getChildren().add(child);
            });
        }

        // Save updated parent (cascades to children)
        Parent updatedParent = parentRepository.save(existingParent);
        logger.info("{} :: Parent Update Successfully Updated",updatedParent.getName());
        return parentMapper.toParentDTO(updatedParent);
    }
	

	// Delete Parent With Child
	@Override
	public void deleteParent(Long id) {
		Parent ExistingParent = parentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Parent not found with ID: " + id));
		parentRepository.deleteById(id);
		logger.info("{} :: Parent Successfully Deleted",ExistingParent.getName());	
	}

	// Search Parent With name and age
	@Override
	public List<ParentDTO> searchParent(String name, Integer age) {
		logger.info("Search Parent By Name and Age :: {}", name, age);
		List<Parent> parents = parentRepository.searchParent(name, age);
		if (parents.isEmpty()) {
            throw new ResourceNotFoundException("No parent found in with Name: " + age);
        }
		//.orElseThrow(() -> new ResourceNotFoundException("Product is not exists with given id :"  + id));
		logger.info("{} :: Parent Successfully Found",parents.size());
		return parents.stream().map(parentMapper::toParentDTO).collect(Collectors.toList());
	}

	
	
	/* This Section For Children */
	
	
	// Search Child With age name dateOfBirth etc
	@Override
	public List<ChildDTO> searchChild(String name, Integer age, String gender, LocalDate dateOfBirth, Long parentId) {

		logger.info("Search Child");
    	List<Child> childrens = childRepository.searchChildren(name, age, gender, dateOfBirth, parentId);
	
    	if (childrens.isEmpty()) {
            throw new ResourceNotFoundException("No children found in with :{} " + age);
        }
		//.orElseThrow(() -> new ResourceNotFoundException("Product is not exists with given id :"  + id));
		logger.info("{} :: Child Successfully Found",childrens.size());
		return childrens.stream().map(parentMapper::toChildDTO).collect(Collectors.toList());
	}
	
	
	
		// Delete child By Id
	
		@Override
		public void deleteChild(Long id) {
			logger.info("Delete child");
			Child child = childRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Child is not exists with given id :"  + id));
			childRepository.delete(child);
			logger.info("{} :: Child Successfully Deleted",child.getName());	
		}
        
		// Get Child By Id
		@Override
		public ChildDTO getChildById(Long id) {
			logger.info("Get Child With Id : {}",id);
			Child child = childRepository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Child is not exists with given id :"  + id));
			return parentMapper.toChildDTO(child);
		}

		
		// Get All Child 
		@Override
		public List<ChildDTO> getAllChild() {
			logger.info("Get All Child ");
			List<Child> childs = childRepository.findAll();
			logger.info("{} :: Child Successfull Found",childs.size());
			return childs.stream().map(parentMapper::toChildDTO).collect(Collectors.toList());
		}

		// Add child With ParentDTO
		@Override
		public ParentDTO addChild(ParentDTO parentDTO) {
			Parent parent = parentMapper.toParentEntity(parentDTO);
			Parent savedParent = parentRepository.save(parent);
			logger.info("{} :: Parent With Child Successfully Add ");
			return parentMapper.toParentDTO(savedParent);
		}

		
		// Add Child With ChildDTO 
		@Override
		public ChildDTO addChildWithChildDTO(ChildDTO childDTO) {
			// TODO Auto-generated method stub
			return null;
		}
		
	
}

		
