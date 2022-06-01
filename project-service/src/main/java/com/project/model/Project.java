package com.project.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Details about the Project")
public class Project implements Serializable {

    @ApiModelProperty(notes = "The Unique ID of the Project's")
    private Long projectID;

    @Temporal(value = TemporalType.DATE)
    @NotNull(message = "Project Start Date can't be null")
    @ApiModelProperty(notes = "The Project's start date")
    private Date projectStartDate;

    @Temporal(value = TemporalType.DATE)
    @NotNull(message = "Project End Date can't be null")
    @ApiModelProperty(notes = "The Project's end date")
    private Date projectEndDate;

    @ApiModelProperty(notes = "The Project's budget alloted")
    private int budgetAllotted;

    @ApiModelProperty(notes = "The Project's budget used")
    private int budgetUsed;

    @NotEmpty(message = "Type of Project can't be empty")
    @NotNull(message = "Type of Project can't be null")
    @ApiModelProperty(notes = "The Project's type whether its a Client or Internal")
    private String typeOfProject;

    @NotEmpty(message = "Project name can't be empty")
    @NotNull(message = "Project name can't be null")
    @ApiModelProperty(notes = "The Project's name")
    private String projectName;

    @ApiModelProperty(notes = "The Unique ID of the User's")
    private long userID;

//    @Enumerated(EnumType.STRING)s
//    private TypeOfProject typeOfProject;
//
//    public Project(long projectID, java.sql.Date projectStartDate, java.sql.Date projectEndDate, int budgetAllotted, int budgetUsed, String string) {
//    }
}