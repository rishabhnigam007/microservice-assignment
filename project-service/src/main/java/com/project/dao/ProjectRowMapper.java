package com.project.dao;

import com.project.model.Project;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectRowMapper implements RowMapper<Project> {

    @Override
    public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
        Project project = new Project();
        project.setProjectID(rs.getLong("projectID"));
        project.setProjectStartDate(rs.getDate("projectStartDate"));
        project.setProjectEndDate(rs.getDate("projectEndDate"));
        project.setBudgetAllotted(rs.getInt("budgetAllotted"));
        project.setBudgetUsed(rs.getInt("budgetUsed"));
        project.setTypeOfProject(rs.getString("typeOfProject"));
        project.setProjectName(rs.getString("projectName"));
        project.setUserID(rs.getLong("userID"));
        return project;
    }
}