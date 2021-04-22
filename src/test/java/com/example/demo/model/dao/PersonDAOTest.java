package com.example.demo.model.dao;

import com.example.demo.model.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@DataJpaTest
@RunWith(SpringRunner.class)
public class PersonDAOTest {

    @Autowired
    private PersonDAO personDAO;

    @Test
    public void shouldNotLoadAllPeople(){
        List<Person> all = personDAO.findAll();
        assertThat(all, hasSize(3));
    }



}
