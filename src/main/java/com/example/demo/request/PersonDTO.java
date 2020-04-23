package com.example.demo.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Person {

	@NotNull(message = "The name is required")
	@Size(max = 10, message = "The max size name is 10 letter")
	@Size(min= 3, message = "The min size name is 3 letter")
	private String name;
	
	@NotNull(message = "The email is required")
	@Size(max = 20, message = "The email max size is 20 letter")
	private String email;
}
