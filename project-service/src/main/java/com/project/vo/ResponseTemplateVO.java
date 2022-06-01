package com.project.vo;

import com.project.model.Project;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Response about the Project as well as User")
public class ResponseTemplateVO {

    @ApiModelProperty(value = "Details about the Project")
    private Project project;
    @ApiModelProperty(value = "Details about the User")
    private User[] users;
}