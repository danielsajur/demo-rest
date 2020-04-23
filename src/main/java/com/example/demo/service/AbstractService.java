package com.example.demo.service;

import java.util.List;

import com.example.demo.model.entity.PersistentEntity;

public interface AbstractService<ID extends Number, E extends PersistentEntity> {

	List<E> getAll() throws Exception;
	
	E getById(ID id) throws Exception;
	
	void delete(E entity);
	
	void deleteById(ID id);
	
	E save(E entity);
	
}
