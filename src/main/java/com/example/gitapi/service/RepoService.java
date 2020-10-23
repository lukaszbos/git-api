package com.example.gitapi.service;

import com.example.gitapi.dto.RepoDto;
import com.example.gitapi.exception.InternalServerError;
import com.example.gitapi.exception.NotFoundException;
import com.example.gitapi.utils.DateFormatter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


@Service
public class RepoService {

    public RepoDto getRepoDto(String url) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        String jsonRepo = getRepo(url, restTemplate);

        Map<String, Object> jsonRepoMap = getMap(jsonRepo);
        LocalDateTime dateTime = DateFormatter.formatLocalDateTime(jsonRepoMap);

        return RepoDto.builder()
                .name((String)jsonRepoMap.get("name"))
                .description((String)jsonRepoMap.get("description"))
                .gitCloneUrl((String)jsonRepoMap.get("clone_url"))
                .stars(Integer.parseInt(jsonRepoMap.get("stargazers_count").toString()))
                .createdAt(dateTime)
                .build();

    }

    private String getRepo(String url, RestTemplate restTemplate) throws HttpClientErrorException.BadRequest {
        String jsonRepo = "";
        try {
            jsonRepo = restTemplate.getForObject(url, String.class);
        } catch (HttpClientErrorException e) {
            throw new NotFoundException("Error, such a repo does not exist!");
        } catch (HttpServerErrorException.InternalServerError e){
            throw new InternalServerError("GitHub API internal server error!");
        }
        return jsonRepo;
    }

    Map<String, Object> getMap(String jsonRepo) throws JsonProcessingException {
        return new ObjectMapper().readValue(jsonRepo, HashMap.class);
    }
}
