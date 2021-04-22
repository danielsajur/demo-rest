package com.example.demo.controller;

import com.example.demo.model.entity.PersistentEntity;
import com.example.demo.request.RequestDTO;
import com.example.demo.response.Response;
import com.example.demo.response.ResponseBuilder;
import com.example.demo.service.AbstractService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Controller<ID extends Number, E extends PersistentEntity, R extends RequestDTO>{

    public static final String DELETED_MESSAGE = "Data deleted successfuly!";
    public static final String UPDATED_MESSAGE = "Data updated successfuly!";
    public static final String SAVED_MESSAGE = "Data saved successfuly!";
    
    private static final int INDEX_ARGUMENT_OF_E = 1;
    private static final int INDEX_ARGUMENT_OF_R = 2;

    protected final AbstractService<ID,E> service;

    public <S> Controller(@Autowired AbstractService<ID,E> service) {
        this.service = service;
    }

    @GetMapping(path = "/list")
    public ResponseEntity<List<R>> getList() throws Exception {

        List<E> all = service.getAll();

        List<R> people = new ArrayList<>();

        all.forEach(E -> {
            R data = instance(INDEX_ARGUMENT_OF_R);
            BeanUtils.copyProperties(E, data);
            people.add(data);
        });

        return ResponseEntity.ok(people);
    }

    @GetMapping
    public ResponseEntity<Response<R>> getAll() throws Exception {

        List<E> all = service.getAll();

        List<R> people = new ArrayList<>();

        all.forEach(E -> {
            R data = instance(INDEX_ARGUMENT_OF_R);
            BeanUtils.copyProperties(E, data);
            people.add(data);
        });

        return new ResponseBuilder<R>().withData(people).build();
    }

    @PostMapping
    public ResponseEntity<Response<R>> add(@Valid @RequestBody R request) {

        E E = instance(INDEX_ARGUMENT_OF_E);
        BeanUtils.copyProperties(request, E);

        E ESaved = service.save(E);

        R data = instance(INDEX_ARGUMENT_OF_R);
        BeanUtils.copyProperties(ESaved, data);

        return new ResponseBuilder<R>().withData(Arrays.asList(data)).withHttp(HttpStatus.CREATED).build();
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Response<R>> get(@PathVariable ID id) throws Exception {

        E E = service.getById(id);
        R data = instance(INDEX_ARGUMENT_OF_R);
        BeanUtils.copyProperties(E, data);

        return new ResponseBuilder<R>().withData(Arrays.asList(data)).build();
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Response<R>> delete(@PathVariable ID id) throws Exception {

        E E = service.getById(id);
        service.delete(E);

        return new ResponseBuilder<R>().withMessage(DELETED_MESSAGE).withHttp(HttpStatus.NO_CONTENT).build();
    }

    private <T> T instance(final int index) {
        Type type = getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) type;
        Type instanceType = paramType.getActualTypeArguments()[index];
        try {
            return ((Class<T>) instanceType).newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
