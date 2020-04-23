package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.response.Response;
import com.example.demo.request.Person;
import com.example.demo.service.PersonService;

@Validated
@RestController(value = PersonController.URI)
public class PersonController {

	public static final String URI = "/persons";
	
	@Autowired
	private PersonService service;
	
	@GetMapping
	public ResponseEntity<Response<Person>> get(@Valid @RequestBody com.example.demo.request.Person person){	
		return null;
	}
}
