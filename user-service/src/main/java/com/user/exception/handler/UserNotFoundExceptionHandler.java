package com.user.exception.handler;

import com.user.exception.ProjectMicroServiceDown;
import com.user.exception.UserAlreadyExistException;
import com.user.exception.UserNotFoundException;
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
public class UserNotFoundExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> exception(UserNotFoundException userNotFoundException) {
        return new ResponseEntity<Object>("User Not Found !!", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UserAlreadyExistException.class)
    public ResponseEntity<Object> exception(UserAlreadyExistException userAlreadyExistException) {
        return new ResponseEntity<Object>("User Already Exist With this Office ID !!", HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler(value = ProjectMicroServiceDown.class)
    public ResponseEntity<Object> exception(ProjectMicroServiceDown projectMicroServiceDown) {
        return new ResponseEntity<>("Project Rest Service is Down", HttpStatus.FORBIDDEN);
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