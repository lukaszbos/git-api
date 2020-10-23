package com.example.gitapi.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RepoControllerTest {
    private WebApplicationContext webApplicationContext;
    private MockMvc mvc;

    @Autowired
    RepoControllerTest(WebApplicationContext webApplicationContext, MockMvc mvc) {
        this.webApplicationContext = webApplicationContext;
        this.mvc = mvc;
    }

    @LocalServerPort
    private int port;

    @BeforeEach
    public void init() {
        this.mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void givenRepoThatExistsWhenGettingRepoThenReturn200() throws Exception {
        String owner = "lukaszbos";
        String repositoryName = "multiplex-api";
        String url = "http://localhost:".concat(String.valueOf(port)).concat("/repos/{owner}/{repository-name}");

        mvc.perform(get(url, owner, repositoryName)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
    }

    @Test
    public void givenRepoThatNotExistsWhenGettingRepoThenReturn404() throws Exception {
        String owner = "randomUser";
        String repositoryName = "/random-api";
        String url = "http://localhost:".concat(String.valueOf(port)).concat("/{owner}/{repository-name}");

        mvc.perform(get(url, owner, repositoryName)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }
}