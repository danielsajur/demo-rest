package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.entity.PersistentEntity;
import com.example.demo.service.AbstractService;

import javassist.NotFoundException;

public class AbstractServiceImpl<ID extends Number, E extends PersistentEntity, DAO extends CrudRepository<E, ID>> implements AbstractService<ID, E> {

	@Autowired
	private DAO dao;
	
	@Override
	public List<E> getAll() {
		return (List<E>) getDao().findAll();
	}

	@Override
	public E getById(ID id) throws Exception {
		Optional<E> entity = dao.findById(id);
		return entity.orElseThrow(() -> new NotFoundException(""));
	}

	@Override
	public void delete(E entity) {
		dao.delete(entity);
	}
	

	@Override
	public void deleteById(ID id) {
		dao.deleteById(id);		
	}

	@Override
	public E save(E entity) {
		return dao.save(entity);
	}

	public DAO getDao() {
		return dao;
	}

}
