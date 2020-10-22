package com.example.gitapi.controler;

import com.example.gitapi.GitApiApplication;
import com.example.gitapi.dto.RepoDto;
import com.example.gitapi.service.RepoService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class RepoControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private RepoController repoController;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RepoService repoServiceMock;

    @BeforeEach
    public void init(){
        System.out.println("Wejszłem do init");
        this.mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }

    //this.restTemplate.getForObject("http://localhost:" + port + "/", String.class)).contains("Hello, World");



    @Test
    public void getRepoIntegrationTest() throws Exception {
//
//
//        String url2 = "http://localhost:" + port + "/repos/ipython/ipython";
//        System.out.println("Rest template to: ");
//        System.out.println(this.restTemplate.getForObject(url2, String.class));


        String owner = "lukaszbos";
        String repositoryName = "multiplex-api";
        String url = "http://localhost:" + port + "/repos/{owner}/{repository-name}";

        final String githubUrl = "https://api.github.com/repos/" + owner + "/" + repositoryName;

        when(repoServiceMock.getRepoDto(url)).thenReturn(new RepoDto());

        mvc.perform(get(url, owner, repositoryName)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();


    }

    @Test
    public void getRepoIntegrationTest_notFound() throws Exception {
        //mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//        MvcResult result = mvc.perform(get)
//        MvcResult result = mvc.perform(get("/administration")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(json)
//                .characterEncoding("utf-8"))
//                .andExpect(status().isOk())
//                .andReturn();
        String username = "lukaszbos";
        String repoName = "muliplex-api";
        String url = "http://localhost:" + port + "/repos/{owner}/{repository-name}";
        mvc.perform(get(url, username, repoName)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();


    }

    @Test
    public void test() throws Exception {
     //   String url = "/repos/ipython/ipython";
        String url = "http://localhost:" + port + "/repos/ipython/ipyvvthon";

        MockMvcBuilders.webAppContextSetup(webApplicationContext).build().perform(MockMvcRequestBuilders
                .get("http://localhostt:" + 8888 + "/repos/ipython/ipyvvthon"))
                .andExpect(status().is2xxSuccessful()).andDo(print());
    }

}