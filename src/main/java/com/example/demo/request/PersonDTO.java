package com.example.demo.request;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PersonDTO implements RequestDTO{

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Long id;
	
	@NotNull(message = "The email is required")
	@Size(max = 50, message = "The email max size is 50 letter")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String email;

	@NotNull(message = "The name is required")
	@Size(min= 3, max = 10, message = "The first name size is between 3 and 10 letter")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String firstName;
	
	@NotNull(message = "The name is required")
	@Size(min =3, max = 10, message = "The last name size is between 3 and 10 letter")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String lastName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
}
