package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.model.dao.PersonDAO;
import com.example.demo.model.entity._Person;
import com.example.demo.service.PersonService;

@Service
class PersonServiceImpl extends AbstractServiceImpl<Long, _Person, PersonDAO> implements PersonService{

}
