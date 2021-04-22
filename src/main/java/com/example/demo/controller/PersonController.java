package com.example.demo.controller;

import com.example.demo.model.entity.Person;
import com.example.demo.request.PersonDTO;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(PersonController.URI)
public class PersonController extends Controller<Long, Person, PersonDTO> {

	public static final String URI = "/people";

	public PersonController(@Autowired PersonService personService) {
		super(personService);
	}
}
