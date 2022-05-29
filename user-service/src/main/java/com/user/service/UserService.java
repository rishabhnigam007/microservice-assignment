package com.user.service;

import com.user.model.User;
import com.user.vo.ResponseTemplateVO;

import javax.validation.Valid;
import java.util.List;

public interface UserService {

    User createUser(@Valid User user);

    void updateUser(@Valid User user);

    void deleteUser(long userID);

    User getUser(long userID);

    List<User> getUsers();

    List<User> getUsersByProjectID(long projectID);

    boolean isUserExist(long userID);

    int isUserExistByOfficeID(@Valid String username);

    ResponseTemplateVO getUserWithProject(long userID);

}