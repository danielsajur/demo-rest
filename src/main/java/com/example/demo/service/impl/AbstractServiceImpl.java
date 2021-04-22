package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import com.example.demo.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.entity.PersistentEntity;
import com.example.demo.service.AbstractService;

import org.springframework.util.CollectionUtils;

public class AbstractServiceImpl<ID extends Number, E extends PersistentEntity, DAO extends JpaRepository<E, ID>> implements AbstractService<ID, E> {

	@Autowired
	private DAO dao;
	
	@Override
	public List<E> getAll() throws NotFoundException {
		List<E> entities =  (List<E>) getDao().findAll();
		if(CollectionUtils.isEmpty(entities)){
			throw new NotFoundException();
		}
		return entities;
	}

	@Override
	public E getById(ID id) throws Exception {
		Optional<E> entity = dao.findById(id);
		return entity.orElseThrow(() -> new NotFoundException());
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
