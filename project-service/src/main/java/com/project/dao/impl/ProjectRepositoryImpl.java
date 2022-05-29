package com.project.dao.impl;

import com.project.dao.ProjectRepository;
import com.project.dao.ProjectRowMapper;
import com.project.model.Project;
import com.project.vo.ResponseTemplateVO;
import com.project.vo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@Repository
@Slf4j
public class ProjectRepositoryImpl implements ProjectRepository {

    private static final String INSERT_PROJECT_QUERY = "INSERT INTO PROJECT(projectid,projectstartdate,projectenddate,budgetallotted,budgetused,typeofproject,projectname,userid) values(?,?,?,?,?,?,?,?)";
    private static final String UPDATE_PROJECT_BY_ID_QUERY = "UPDATE PROJECT SET budgetused=? WHERE projectid=?";
    private static final String GET_PROJECT_BY_ID_QUERY = "SELECT * FROM PROJECT WHERE projectid=?";
    private static final String DELETE_PROJECT_BY_ID_QUERY = "DELETE FROM PROJECT WHERE projectid=?";
    private static final String GET_PROJECTS_QUERY = "SELECT * FROM PROJECT";
    private static final String IS_PROJECT_EXIST_BY_PROJECT_NAME = "SELECT COUNT(*) FROM PROJECT WHERE projectname=?";
    private static final String IS_PROJECT_EXIST_BY_PROJECT_ID = "SELECT COUNT(*) FROM PROJECT WHERE projectid=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RestTemplate restTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public String createTable() {
        log.info("Inside Create Table method in project repository impl !!");
        String createTableQuery = "CREATE TABLE IF NOT EXISTS Project (projectid bigint primary key, projectstartdate date, projectenddate date, budgetallotted int, budgetused int, typeofproject varchar(45), projectname varchar(45), userid int not null)";
        int createTable = getJdbcTemplate().update(createTableQuery);
        if (createTable == 0) {
            log.info("Project Table Created Successfully !!");
            return "Project Table Created Successfully !!";
        } else {
            log.error("Project Table Not Created");
            return "Project Table Not Created";
        }
    }

    @Override
    public Project createProject(@Valid Project project) {
        log.info("Inside create project method in project repository impl !!");
        getJdbcTemplate().update(INSERT_PROJECT_QUERY, project.getProjectID(), project.getProjectStartDate(), project.getProjectEndDate(), project.getBudgetAllotted(), project.getBudgetUsed(), project.getTypeOfProject(), project.getProjectName(), project.getUserID());
        return project;
    }

    @Override
    public Project updateProject(@Valid Project project) {
        log.info("Inside update project method in project repository impl !!");
        getJdbcTemplate().update(UPDATE_PROJECT_BY_ID_QUERY, project.getBudgetUsed(), project.getProjectID());
        return project;
    }

    @Override
    public Project fetchProjectById(Long projectId) {
        log.info("Inside Fetch Project By Id method in project repository impl !!");
        return getJdbcTemplate().queryForObject(GET_PROJECT_BY_ID_QUERY, new ProjectRowMapper(), projectId);
    }

    @Override
    public List<Project> fetchAllProjects() {
        log.info("Inside Fetch All Projects method in project repository impl !!");
        return getJdbcTemplate().query(GET_PROJECTS_QUERY, new ProjectRowMapper());
    }

    @Override
    public String deleteProjectById(Long projectId) {
        log.info("Inside Delete Project By ID method in project repository impl !!");
        getJdbcTemplate().update(DELETE_PROJECT_BY_ID_QUERY, projectId);
        return "Project got deleted with projectid  : " + projectId;
    }

    @Override
    public boolean isProjectExistByProjectName(String projectname) {
        log.info("Inside isProjectExistByProjectName method in service !!");
        // It will return 1 if record is present
        int count = getJdbcTemplate().queryForObject(IS_PROJECT_EXIST_BY_PROJECT_NAME, new Object[]{projectname}, Integer.class);
        if (count >= 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isProjectExistByProjectID(Long projectid) {
        log.info("Inside is project by id method in project repository impl !!");
        // It will return 1 if record is present
        int count = getJdbcTemplate().queryForObject(IS_PROJECT_EXIST_BY_PROJECT_ID, new Object[]{projectid}, Integer.class);
        if (count >= 1) {
            return true;
        }
        return false;
    }

    @Override
    public ResponseTemplateVO getAllUserWithProject(long projectID) {
        log.info("Inside get all user with project id method in project repository impl !!");
        ResponseTemplateVO responseTemplateVO = new ResponseTemplateVO();
        Project project = getJdbcTemplate().queryForObject(GET_PROJECT_BY_ID_QUERY, new ProjectRowMapper(), projectID);
        log.info("Project successfully get using project id");
        User[] userList = (User[]) restTemplate.getForObject("http://user-service/user/getallusersbyprojectid/" + project.getProjectID(), User[].class);
        log.info("User list successfully get using restTemplate calling !!");
        responseTemplateVO.setProject(project);
        responseTemplateVO.setUsers(userList);
        return responseTemplateVO;
    }
}