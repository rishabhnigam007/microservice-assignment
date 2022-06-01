package com.springsecurity.controller;

import com.springsecurity.model.Login;
import com.springsecurity.repository.LoginRepository;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @Autowired
    private LoginRepository loginRepository;

    @PostMapping("/createuser")
    @ApiOperation(value = "Creating a User Login",
            notes = "Provide a Login request body for creating a User Login",
            response = Login.class)
    public Login createUser(@Valid @RequestBody Login login) {
        log.info("Inside create login method in controller !!");
        return loginRepository.save(login);
    }

    @GetMapping("/getallusers")
    @ApiOperation(value = "Find All Users",
            notes = "Getting a list of Users",
            response = Login.class)
    public List<Login> getAllUsers() {
        log.info("Inside get all user method in controller !!");
        return loginRepository.findAll();
    }

}