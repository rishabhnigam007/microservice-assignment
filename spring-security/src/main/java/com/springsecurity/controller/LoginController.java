package com.springsecurity.controller;

import com.springsecurity.model.Login;
import com.springsecurity.repository.LoginRepository;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/createuser")
    @ApiOperation(value = "Creating a User Login",
            notes = "Provide a Login request body for creating a User Login",
            response = Login.class)
    public Login createUser(@Valid @RequestBody Login login) {
        log.info("Inside create login method in controller !!");
        login.setPassword(bCryptPasswordEncoder.encode(login.getPassword()));
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

    @GetMapping("/getuserbyid/{userID}")
    @ApiOperation(value = "Find User By ID",
            notes = "Getting a User By ID Specific",
            response = Login.class)
    public Login getUserByID(@PathVariable("userID") long userID) {
        log.info("Inside get user by id method in controller !!");
        Optional<Login> op = loginRepository.findById(userID);
        Login login = op.get();
        return login;
    }

}