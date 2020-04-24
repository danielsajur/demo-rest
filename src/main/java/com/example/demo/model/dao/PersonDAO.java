package com.example.demo.model.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.entity.Person;

public interface PersonDAO extends CrudRepository<Person, Long>{

}
