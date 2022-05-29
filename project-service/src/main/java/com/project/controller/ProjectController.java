package com.project.controller;

import com.project.dao.impl.ProjectRepositoryImpl;
import com.project.exception.ProjectAlreadyExistException;
import com.project.exception.ProjectNotFoundException;
import com.project.model.Project;
import com.project.vo.ResponseTemplateVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/project")
@Slf4j
public class ProjectController {

    @Autowired
    ProjectRepositoryImpl projectRepository;

    @PostMapping("/createproject")
    public Project addProject(@Valid @RequestBody Project project) {
        log.info("Inside add project method in controller !!");
        boolean isExistByProjectName = projectRepository.isProjectExistByProjectName(project.getProjectName());
        if (isExistByProjectName) {
            log.error("Project already exist with this project name : " + project.getProjectName());
            throw new ProjectAlreadyExistException();
        }
        log.info("Project created successfully !!");
        return projectRepository.createProject(project);
    }

    @PutMapping("/updateproject/{id}")
    public Project updateProject(@PathVariable("id") long id, @Valid @RequestBody Project project) {
        log.info("Inside update project method in controller !!");
        boolean isExistByProjectID = projectRepository.isProjectExistByProjectID(id);
        if (isExistByProjectID) {
            log.info("Project update successfully with this id : " + id);
            return projectRepository.updateProject(project);
        }
        log.error("Project not found for this id : " + id);
        throw new ProjectNotFoundException();
    }

    @GetMapping("/getbyprojectid/{id}")
    public Project getProject(@PathVariable("id") long id) {
        log.info("Inside Get project By ID method in controller !!");
        boolean isExistByProjectID = projectRepository.isProjectExistByProjectID(id);
        if (isExistByProjectID) {
            log.info("Project successfully get for this id : " + id);
            return projectRepository.fetchProjectById(id);
        }
        log.error("Project not found for this id : " + id);
        throw new ProjectNotFoundException();
    }

    @GetMapping("/allprojects")
    public List<Project> getProjects() {
        log.info("Inside Get All Project method in controller !!");
        return projectRepository.fetchAllProjects();
    }

    @DeleteMapping("/deletebyprojectid/{id}")
    public String deleteProject(@PathVariable("id") long id) {
        log.info("Inside Delete By ID Project method in controller !!");
        boolean isExistByProjectID = projectRepository.isProjectExistByProjectID(id);
        if (isExistByProjectID) {
            log.info("Project successfully deleted for id : " + id);
            return projectRepository.deleteProjectById(id);
        }
        log.error("Project Not Found for delete with this id : " + id);
        throw new ProjectNotFoundException();
    }

    @GetMapping("/getallusersbyprojectid/{projectID}")
    public ResponseTemplateVO getAllUserByProjectID(@PathVariable("projectID") long projectID) {
        log.info("Inside Get All User By Project ID method in controller !!");
        boolean isExistByProjectID = projectRepository.isProjectExistByProjectID(projectID);
        if (isExistByProjectID) {
            log.info("Successfully get all user with this project id : " + projectID);
            return projectRepository.getAllUserWithProject(projectID);
        }
        log.error("Project not found for this project id : " + projectID);
        throw new ProjectNotFoundException();
    }
}