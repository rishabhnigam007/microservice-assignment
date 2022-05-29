package com.project.dao;

import com.project.model.Project;
import com.project.vo.ResponseTemplateVO;

import javax.validation.Valid;
import java.util.List;

public interface ProjectRepository {

    Project createProject(@Valid Project project);

    Project updateProject(@Valid Project project);

    Project fetchProjectById(Long projectId);

    List<Project> fetchAllProjects();

    String deleteProjectById(Long projectId);

    boolean isProjectExistByProjectName(String projectName);

    boolean isProjectExistByProjectID(Long projectid);

    ResponseTemplateVO getAllUserWithProject(long projectID);
}