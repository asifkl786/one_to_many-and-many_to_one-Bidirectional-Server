package com.releation.mapping.entity;



import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;
    private String gender;
    private LocalDate dateOfBirth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Parent parent;

    
    
	public Child() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Child(Long id, String name, Integer age, String gender, LocalDate dateOfBirth, Parent parent) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.parent = parent;
	}



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



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}



	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}



	public Parent getParent() {
		return parent;
	}



	public void setParent(Parent parent) {
		this.parent = parent;
	}



	@Override
	public String toString() {
		return "Child [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + ", dateOfBirth="
				+ dateOfBirth + ", parent=" + parent + "]";
	}    
}

