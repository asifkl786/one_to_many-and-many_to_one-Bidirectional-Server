package com.releation.mapping.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.releation.mapping.entity.Child;

@Repository
public interface ChildRepository extends JpaRepository<Child, Long> {
	
    @Query("SELECT c FROM Child c WHERE " +
           "(:name IS NULL OR c.name = :name) AND " +
           "(:age IS NULL OR c.age = :age) AND " +
           "(:gender IS NULL OR c.gender = :gender) AND " +
           "(:dateOfBirth IS NULL OR c.dateOfBirth = :dateOfBirth) AND " +
           "(:parentId IS NULL OR c.parent.id = :parentId)")
    List<Child> searchChildren(@Param("name") String name,
                               @Param("age") Integer age,
                               @Param("gender") String gender,
                               @Param("dateOfBirth") LocalDate dateOfBirth,
                               @Param("parentId") Long parentId);
}

