package com.user.controller;

import com.user.exception.ProjectMicroServiceDown;
import com.user.exception.UserAlreadyExistException;
import com.user.exception.UserNotFoundException;
import com.user.model.User;
import com.user.service.impl.UserServiceImpl;
import com.user.vo.ResponseTemplateVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/createuser")
    @ApiOperation(value = "Creating a User",
            notes = "Provide a User request body for creating a User",
            response = User.class)
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        log.info("Inside create user method in controller !!");
        int isExist = userService.isUserExistByOfficeID(user.getOfficeID());
        if (isExist != 0) {
            log.error("User already exist !!");
            throw new UserAlreadyExistException();
        } else {
            user = userService.createUser(user);
            log.info("User created successfully !!");
            return new ResponseEntity<Object>(user, HttpStatus.CREATED);
        }
    }

    @PutMapping("/updateuser/{userID}")
    @ApiOperation(value = "Updating a User",
            notes = "Updating a User using user ID",
            response = User.class)
    public ResponseEntity<Object> updateUser(@ApiParam(value = "ID value for the User you need to update", required = true) @PathVariable("userID") long userID, @Valid @RequestBody User user) {
        log.info("Inside update user method in controller !!");
        boolean isExist = userService.isUserExist(userID);
        if (isExist) {
            log.info("User exist with this id");
            user.setUserID(userID);
            userService.updateUser(user);
            log.info("User Successfully updated with id : " + userID);
            return new ResponseEntity<Object>("User Updated successfully : \n" + user, HttpStatus.OK);
        } else {
            log.error("User Not found with this id : " + userID);
            throw new UserNotFoundException();
        }
    }

    @DeleteMapping("/deleteuser/{userID}")
    @ApiOperation(value = "Deleting a User",
            notes = "Deleting a User using user ID",
            response = User.class)
    public ResponseEntity<Object> deleteUser(@ApiParam(value = "ID value for the User you need to delete", required = true) @PathVariable("userID") long userID) {
        log.info("Inside delete user method controller !!");
        boolean isExist = userService.isUserExist(userID);
        if (isExist) {
            log.info("User exist with this id");
            User user = userService.getUser(userID);
            userService.deleteUser(userID);
            log.info("User successfully deleted with id : " + userID);
            return new ResponseEntity<Object>(user, HttpStatus.GONE);
        } else {
            log.error("User not found with this id : " + userID);
            throw new UserNotFoundException();
        }
    }

    @GetMapping("/getuserbyid/{userID}")
    @ApiOperation(value = "Find User by ID",
            notes = "Provide an ID to look up specific User",
            response = User.class)
    public ResponseEntity<Object> getUserById(@ApiParam(value = "ID value for the User you need to retrieve", required = true) @PathVariable("userID") long userID) {
        log.info("Inside get user by id method in controller !!");
        boolean isExist = userService.isUserExist(userID);
        if (isExist) {
            log.info("User exist with this id");
            User user = userService.getUser(userID);
            log.info("User getting successfully with id : " + userID);
            return new ResponseEntity<Object>(user, HttpStatus.OK);
        } else {
            log.error("User not found with this id : " + userID);
            throw new UserNotFoundException();
        }
    }

    @GetMapping("/getallusers")
    @ApiOperation(value = "Find All Users",
            notes = "Getting a list of Users",
            response = User.class)
    public ResponseEntity<Object> getAllUsers() {
        log.info("Inside get all user method in controller !!");
        List<User> userList = userService.getUsers();
        if (userList.isEmpty()) {
            log.error("User list is empty");
            throw new UserNotFoundException();
        } else {
            log.info("Getting all user list");
            return new ResponseEntity<Object>(userList, HttpStatus.OK);
        }
    }

    @GetMapping("/getallusersbyprojectid/{projectID}")
    @ApiOperation(value = "Find all Users by Project ID",
            notes = "Getting a list of Users who allocated in same Project",
            response = User.class)
    public ResponseEntity<Object> getAllUsersByProjectID(@ApiParam(value = "Project ID value for the all User you need to retrieve for specific Project", required = true) @PathVariable("projectID") long projectID) {
        log.info("Inside getallusersbyprojectid method in controller !!");
        List<User> userList = userService.getUsersByProjectID(projectID);
        if (userList.isEmpty()) {
            log.error("user not found with this project id : " + projectID);
            throw new UserNotFoundException();
        } else {
            log.info("User list is geting with this project id : " + projectID);
            return new ResponseEntity<Object>(userList, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/getuserwithproject/{projectID}")
    @ApiOperation(value = "Find all Users by Project ID along with Project details",
            notes = "Getting a list of Users who allocated in same Project along with Project Details",
            response = User.class)
    public ResponseTemplateVO getUserWithProject(@ApiParam(value = "Project ID value for the all User you need to retrieve for specific Project", required = true) @PathVariable("projectID") long projectID) throws ProjectMicroServiceDown {
        log.info("Inside getUserWithProject of User Controller");
        return userService.getUserWithProject(projectID);
    }

}