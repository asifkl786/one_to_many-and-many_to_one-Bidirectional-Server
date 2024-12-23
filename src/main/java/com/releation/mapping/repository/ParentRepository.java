package com.releation.mapping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.releation.mapping.entity.Parent;


@Repository
public interface ParentRepository extends JpaRepository<Parent,Long> {
	
	/*
	 // Find by parent name (case-insensitive)
    @Query("SELECT p FROM Parent p WHERE p.name LIKE %:name%")
    List<Parent> searchParentByName(@Param("name") String name);
    
    // Find by parent age
    @Query("SELECT p FROM Parent p WHERE p.age = :age")
    List<Parent> findByAge(@Param("age") Integer age);
    
    */
    
    // Find By name and age
    @Query("SELECT c FROM Parent c WHERE " +
            "(:name IS NULL OR c.name = :name) AND " +
            "(:age IS NULL OR c.age = :age)")
     List<Parent> searchParent(@Param("name") String name,@Param("age") Integer age);

}
