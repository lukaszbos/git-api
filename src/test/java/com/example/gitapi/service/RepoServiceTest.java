package com.example.gitapi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest
public class RepoServiceTest {
    @Test
    public void givenJsonMapWhenGettingMapThenReturnTrue () throws JsonProcessingException {
        RepoService repoService = new RepoService();
        String jsonRepo = "{\"id\":658518,\"node_id\":\"MDEwOlJlcG9zaXRvcnk2NTg1MTg=\",\"name\":\"ipython\",\"full_name\":\"ipython\"}";
        Assert.assertTrue(repoService.getMap(jsonRepo) instanceof Map);
    }
}
