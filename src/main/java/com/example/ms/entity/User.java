package com.example.ms.entity;

import javax.persistence.*;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity(name = "user_details")
public class User {
	@Id
	@GeneratedValue
	private Integer id;

	@Size(min = 1, message = "User name should have atleast one character")
	@Column
	private String name;

	@PastOrPresent(message = "User's birth date should be in past or at present")
	@Column(name = "birth_date")
	private LocalDate birthDate;

	public User(Integer id, String name, LocalDate birthdate) {
		this.id = id;
		this.name = name;
		this.birthDate = birthdate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthdate) {
		this.birthDate = birthdate;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", birthdate=" + birthDate +
				'}';
	}
}
