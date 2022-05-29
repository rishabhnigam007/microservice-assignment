package com.user.service.impl;

import com.user.model.User;
import com.user.repository.UserRepository;
import com.user.service.UserService;
import com.user.vo.Project;
import com.user.vo.ResponseTemplateVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResponseTemplateVO getUserWithProject(long userID) {
        log.info("Inside getUserWithProject method in service");
        ResponseTemplateVO responseTemplateVO = new ResponseTemplateVO();
        User user = userRepository.findById(userID).get();
        log.info("User find by this id : " + userID);
        Project project = restTemplate.getForObject("http://project-service/project/getbyprojectid/" + user.getProjectID(), Project.class);
        log.info("Calling project-service microservice using RestTemplate !!");
        log.info("Project get with this user id : " + userID);
        responseTemplateVO.setUser(user);
        responseTemplateVO.setProject(project);
        log.info("User and Project successfully get and returned");
        return responseTemplateVO;
    }

    @Override
    public User createUser(@Valid User user) {
        log.info("Inside create user method in service !!");
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void updateUser(@Valid User user) {
        log.info("Inside update user method in service");
        userRepository.saveAndFlush(user);
    }

    @Override
    public void deleteUser(long userID) {
        log.info("Inside delete user method in service");
        userRepository.deleteById(userID);
    }

    @Override
    public User getUser(long userID) {
        log.info("Inside get user method in service");
        Optional<User> op = userRepository.findById(userID);
        User e = op.get();
        return e;
    }

    @Override
    public List<User> getUsers() {
        log.info("Inside getUsers method in service");
        List<User> allUsers = userRepository.findAll();
        return allUsers;
    }

    @Override
    public List<User> getUsersByProjectID(long projectID) {
        log.info("Inside getuserbyprojectid method in service !!");
        List<User> allUsersBySameProjectID = userRepository.allUsersBySameProjectID(projectID);
        log.info("List of user getting and returning");
        return allUsersBySameProjectID;
    }

    @Override
    public boolean isUserExist(long userID) {
        log.info("Inside isUserExist method in service");
        return userRepository.existsById(userID);
    }

    @Override
    public int isUserExistByOfficeID(@Valid String officeID) {
        log.info("Inside isUserExistByOfficeID method in service !!");
        return userRepository.existsByOfficeID(officeID);
    }
}