package com.example.demo.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.net.URI;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final String personJson = "{\"email\":\"daniel.sajur@gmail.com\",\"firstName\":\"Daniel\",\"lastName\":\"Junior\"}";

    @Test
    public void shouldAddOnePerson() throws Exception {
        URI uri = new URI(PersonController.URI);
        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(personJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status().is(HttpStatus.CREATED.value()));
    }

    @Test
    public void shouldReturnAllPeople() throws Exception {
        URI uri = new URI(PersonController.URI);
        mockMvc.perform(MockMvcRequestBuilders
                .get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status().is(HttpStatus.OK.value()));
    }

    @Test
    public void shouldReturnAllPeopleList() throws Exception {
        URI uri = new URI(PersonController.URI + "/list");
        mockMvc.perform(MockMvcRequestBuilders
                .get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status().is(HttpStatus.OK.value()));
    }

    @Test
    public void shouldReturnOnePerson() throws Exception {
        URI uri = new URI(PersonController.URI + "/200");
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
                                                            .get(uri)
                                                            .contentType(MediaType.APPLICATION_JSON))
                                                            .andReturn()
                                                            .getResponse();
        Assert.assertNotNull(response);
        Assert.assertTrue(StringUtils.isNotBlank(response.getContentAsString()));
        Assert.assertTrue(response.getContentAsString().contains("\"id\":200"));
    }

    @Test
    public void shouldReturnNobody() throws Exception {
        URI uri = new URI(PersonController.URI + "/2000");
        mockMvc.perform(MockMvcRequestBuilders
                        .get(uri)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers
                                .status().is(HttpStatus.NO_CONTENT.value()));
    }

    @Test
    public void shouldReturnDeleteOnePerson() throws Exception {
        URI uri = new URI(PersonController.URI + "/100");
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
                .delete(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();

        Assert.assertNotNull(response);
        Assert.assertTrue(StringUtils.isNotBlank(response.getContentAsString()));
        Assert.assertTrue(response.getContentAsString().contains(PersonController.DELETED_MESSAGE));

        mockMvc.perform(MockMvcRequestBuilders
                .get(uri)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                            .status()
                            .is(HttpStatus.NO_CONTENT.value()));
    }

}
