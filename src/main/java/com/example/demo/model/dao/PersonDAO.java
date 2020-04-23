package com.example.demo.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.entity._Person;

public interface PersonDAO extends CrudRepository<_Person, Long>{

	void update(_Person person);
}
