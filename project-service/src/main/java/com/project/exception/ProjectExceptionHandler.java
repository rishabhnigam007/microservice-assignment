package com.project.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class ProjectExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ProjectNotFoundException.class)
    public ResponseEntity<Object> exception(ProjectNotFoundException projectNotFoundException) {
        return new ResponseEntity<Object>("Project Not Found !!", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ProjectAlreadyExistException.class)
    public ResponseEntity<Object> exception(ProjectAlreadyExistException projectAlreadyExistException) {
        return new ResponseEntity<Object>("Project Already Exist With this Project Name !!", HttpStatus.ALREADY_REPORTED);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error("Constraint Voilation Error !!");

        Map<String, List<String>> body = new HashMap<>();

        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());

        body.put("Constraint Voilation Error", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

}