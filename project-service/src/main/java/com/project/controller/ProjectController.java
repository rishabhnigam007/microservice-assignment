package com.project.controller;

import com.project.dao.impl.ProjectRepositoryImpl;
import com.project.exception.ProjectAlreadyExistException;
import com.project.exception.ProjectNotFoundException;
import com.project.model.Project;
import com.project.vo.ResponseTemplateVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    @ApiOperation(value = "Creating a Project",
            notes = "Provide a Project request body for creating a project",
            response = Project.class)
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
    @ApiOperation(value = "Updating a Project",
            notes = "Updating a Project using project ID",
            response = Project.class)
    public Project updateProject(@ApiParam(value = "ID value for the Project you need to update", required = true) @PathVariable("id") long id, @Valid @RequestBody Project project) {
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
    @ApiOperation(value = "Find project by ID",
            notes = "Provide an ID to look up specific Project",
            response = Project.class)
    public Project getProject(@ApiParam(value = "ID value for the Project you need to retrieve", required = true) @PathVariable("id") long id) {
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
    @ApiOperation(value = "Find All Projects",
            notes = "Getting a list of Projects",
            response = Project.class)
    public List<Project> getProjects() {
        log.info("Inside Get All Project method in controller !!");
        return projectRepository.fetchAllProjects();
    }

    @DeleteMapping("/deletebyprojectid/{id}")
    @ApiOperation(value = "Deleting a Project",
            notes = "Deleting a Project using Project ID",
            response = Project.class)
    public String deleteProject(@ApiParam(value = "ID value for the Project you need to delete", required = true) @PathVariable("id") long id) {
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
    @ApiOperation(value = "Find all Project By ID as well as Users along with User details",
            notes = "Getting a list of Users who allocated in same Project along with Project Details",
            response = Project.class)
    public ResponseTemplateVO getAllUserByProjectID(@ApiParam(value = "Project ID value for the all User you need to retrieve for specific Project", required = true) @PathVariable("projectID") long projectID) {
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