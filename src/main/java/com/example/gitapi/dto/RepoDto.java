package com.example.gitapi.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@Data
public class RepoDto {
    String name;
    String description;
    String gitCloneUrl;
    int stars;
    LocalDateTime createdAt;


    public RepoDto(String name, String description, String gitCloneUrl, int stars, LocalDateTime createdAt) {
        this.name = name;
        this.description = description;
        this.gitCloneUrl = gitCloneUrl;
        this.stars = stars;
        this.createdAt = createdAt;
    }

}
