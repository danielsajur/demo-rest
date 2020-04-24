package com.example.demo.controller;

import com.example.demo.model.entity.Person;
import com.example.demo.request.PersonDTO;
import com.example.demo.response.Response;
import com.example.demo.response.ResponseBuilder;
import com.example.demo.service.PersonService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Validated
@RestController
@RequestMapping(PersonController.URI)
public class PersonController {

	private static final String DELETED_MESSAGE = "Data deleted successfuly!";
	private static final String UPDATED_MESSAGE = "Data updated successfuly!";
	private static final String SAVED_MESSAGE = "Data saved successfuly!";

	public static final String URI = "/people";

	@Autowired
	private PersonService service;

	@GetMapping
	public ResponseEntity<Response<PersonDTO>> getAll() throws Exception {

		List<Person> all = service.getAll();

		List<PersonDTO> people = new ArrayList<PersonDTO>();

		all.forEach(person -> {
			PersonDTO data = new PersonDTO();
			BeanUtils.copyProperties(person, data);
			people.add(data);
		});

		return new ResponseBuilder<PersonDTO>().withData(people).build();
	}


	@PostMapping
	public ResponseEntity<Response<PersonDTO>> add(@Valid @RequestBody PersonDTO request) {

		Person person = new Person();
		BeanUtils.copyProperties(request, person);

		Person personSaved = service.save(person);

		PersonDTO data = new PersonDTO();
		BeanUtils.copyProperties(personSaved, data);

		return new ResponseBuilder<PersonDTO>().withData(Arrays.asList(data)).withHttp(HttpStatus.CREATED).build();
	}

	@GetMapping(value = "{id}")
	public ResponseEntity<Response<PersonDTO>> get(@PathVariable Long id) throws Exception {

		Person person = service.getById(id);
		PersonDTO data = new PersonDTO();
		BeanUtils.copyProperties(person, data);

		return new ResponseBuilder<PersonDTO>().withData(Arrays.asList(data)).build();
	}
	
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Response<PersonDTO>> delete(@PathVariable Long id) throws Exception {

		Person person = service.getById(id);
		service.delete(person);

		return new ResponseBuilder<PersonDTO>().withMessage(DELETED_MESSAGE).build();
	}

}
