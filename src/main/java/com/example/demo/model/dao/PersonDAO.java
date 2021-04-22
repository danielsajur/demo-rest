package com.example.demo.model.dao;

import com.example.demo.model.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PersonDAO extends JpaSpecificationExecutor<Person>, JpaRepository<Person, Long> {

}
