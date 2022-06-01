package com.user.vo;

import com.user.model.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Response about the User as well as Project")
public class ResponseTemplateVO {

    @ApiModelProperty(value = "Details about the User")
    private User user;
    @ApiModelProperty(value = "Details about the Project")
    private Project project;

}