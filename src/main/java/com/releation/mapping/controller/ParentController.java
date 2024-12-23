package com.releation.mapping.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.releation.mapping.dto.ChildDTO;
import com.releation.mapping.dto.ParentDTO;
import com.releation.mapping.service.ParentService;

import lombok.AllArgsConstructor;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/parents")
public class ParentController {
	
	private static final Logger logger = LoggerFactory.getLogger(ParentController.class);
	
	@Autowired
	ParentService parentService;
	
	
	// Build Add Parent REST API
	@PostMapping("/add")
	public ResponseEntity<ParentDTO> addParent(@RequestBody ParentDTO parentDTO){
		logger.info("Recived Request to Add Parent With Name : {}",parentDTO.getName());
		ParentDTO parent = parentService.addParent(parentDTO);
		return new ResponseEntity<>(parent,HttpStatus.CREATED);
	}
	
	// Build Get Parent By Id REST API
	@GetMapping("/getParent/{id}")
	public ResponseEntity<ParentDTO> getParentById(@PathVariable Long id){
		logger.info("Recived Request to Get Parent By Id :: {}",id);
		ParentDTO parent = parentService.getParentById(id);
		return new ResponseEntity<>(parent, HttpStatus.OK);
	}
	
	// Build Get All Parent 
	@GetMapping("/getAll")
	public ResponseEntity<List<ParentDTO>> getAllParent(){
		logger.info("Recived Request to Get All Parent");
		List<ParentDTO> parents = parentService.getAllParent();
		return new ResponseEntity<>(parents,HttpStatus.OK);
	}
	
	
	// Build Update Parent with child REST API
	@PutMapping("/update/{id}")
	public ResponseEntity<ParentDTO> updateParent(@PathVariable Long id, @RequestBody ParentDTO parentDTO){
		logger.info("Recived Request to Update Parent with id :{} ",id);
		ParentDTO updatedParent = parentService.updateParent(id, parentDTO);
		return new ResponseEntity<>(updatedParent,HttpStatus.OK);
	}
	
	// Build Delete Parent With Child REST API
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		parentService.deleteParent(id);
		return new ResponseEntity<>("Parent Successfully Delete ", HttpStatus.OK);
	}
	
	// Build Search Parent By Name And Age REST API
	@GetMapping("/searchParent")
	public ResponseEntity<List<ParentDTO>> searchParentByName(@RequestParam(required = false) String name,
			                                                  @RequestParam(required = false) Integer age){
		logger.info("Recived Request to Search Parent By Name and Age :: {}");
		List<ParentDTO> parents = parentService.searchParent(name,age);
		return new ResponseEntity<>(parents, HttpStatus.OK);
	}
	
	
	
	/* This Section For Children */
	
	
	
	
	// Build Search Children By Name Age gender dateOfBirth parentId REST API
		@GetMapping("/searchChildren")
		public ResponseEntity<List<ChildDTO>> searchChildren(
				@RequestParam(required = false) String name,
	            @RequestParam(required = false) Integer age,
	            @RequestParam(required = false) String gender,
	            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateOfBirth,
	            @RequestParam(required = false) Long parentId) {
			logger.info("Recived Request to Search Children By :: {}");
			List<ChildDTO> childrens = parentService.searchChild(name, age, gender, dateOfBirth, parentId);
			return new ResponseEntity<>(childrens,HttpStatus.OK);
		}
	
		
		// Build Delete Children By Id REST API
		@DeleteMapping("/deleteChild/{id}")
		public ResponseEntity<String> deleteChild(@PathVariable Long id){
			logger.info("Recived Request to Delete Child with id :",id);
			parentService.deleteChild(id);
			return new ResponseEntity<>("{} :: Child Successfully deleted", HttpStatus.OK);
		}
		
		// Build get Child by id REST API
		@GetMapping("/getChild/{id}")
		public ResponseEntity<ChildDTO> getChildById(@PathVariable Long id){
			logger.info("Recived Request to get child By Id : {}",id);
			 ChildDTO child = parentService.getChildById(id);
			return new ResponseEntity<>(child, HttpStatus.OK);
		} 
		
		// Build get All Child REST API
		@GetMapping("/getAllChild")
		public ResponseEntity<List<ChildDTO>> getAllChild(){
			logger.info("Recived Request to get all child");
			List<ChildDTO> childs = parentService.getAllChild();
			return new ResponseEntity<>(childs, HttpStatus.OK );	
		}
		
		// Build Add Child REST API
		@PostMapping("/addChild")
		public ResponseEntity<ParentDTO> addChild(@RequestBody ParentDTO parentDTO){
			logger.info("Recived Request to Add Child With Name :: {}", parentDTO.getName());
			ParentDTO parent = parentService.addChild(parentDTO);
			return new ResponseEntity<>(parent, HttpStatus.CREATED);
		}

}
