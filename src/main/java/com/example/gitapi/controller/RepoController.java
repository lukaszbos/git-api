package com.example.gitapi.controller;

import com.example.gitapi.dto.RepoDto;
import com.example.gitapi.service.RepoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/repos")
public class RepoController {

    private final RepoService repoService;

    @Autowired
    public RepoController(RepoService repoService) {
        this.repoService = repoService;
    }


    @GetMapping("/{owner}/{repository-name}")
    public ResponseEntity<RepoDto> getRepo(@PathVariable String owner, @PathVariable("repository-name") String repositoryName) throws JsonProcessingException {
        final String url = "https://api.github.com/repos/".concat(owner).concat("/").concat(repositoryName);
        RepoDto repoDto = repoService.getRepoDto(url);

        return new ResponseEntity<>(repoDto, HttpStatus.OK);
    }

}
