package com.example.gitapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@Data
@NoArgsConstructor
public class RepoDto {
    String name;
    String description;
    String gitCloneUrl;
    int stars;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    LocalDateTime createdAt;

    public RepoDto(String name, String description, String gitCloneUrl, int stars, LocalDateTime createdAt) {
        this.name = name;
        this.description = description;
        this.gitCloneUrl = gitCloneUrl;
        this.stars = stars;
        this.createdAt = createdAt;
    }

}
