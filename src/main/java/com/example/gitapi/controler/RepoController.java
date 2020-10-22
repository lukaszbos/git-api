package com.example.gitapi.controler;

import com.example.gitapi.dto.RepoDto;
import com.example.gitapi.error.ApiError;
import com.example.gitapi.exception.NotFoundException;
import com.example.gitapi.service.RepoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.NOT_FOUND;


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
        final String url = "https://api.github.com/repos/" + owner + "/" + repositoryName;
        RepoDto repoDto = repoService.getRepoDto(url);

        return new ResponseEntity<>(repoDto, HttpStatus.ACCEPTED);
    }

    @ControllerAdvice
    public class RestExceptionHandler extends ResponseEntityExceptionHandler {

        @ExceptionHandler(NotFoundException.class)
        protected ResponseEntity<Object> handleEntityNotFound(NotFoundException ex) {
            ApiError apiError = new ApiError(NOT_FOUND);
            apiError.setMessage(ex.getMessage());
            return buildResponseEntity(apiError);
        }

        @Override
        protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
            String error = "Malformed JSON request";
            return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
        }

        private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
            return new ResponseEntity<>(apiError, apiError.getStatus());
        }
    }
}
