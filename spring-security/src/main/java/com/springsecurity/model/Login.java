package com.springsecurity.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "LOGIN")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Details about the Login")
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The Unique ID of the Login's")
    private long userID;

    @NotNull(message = "Username field should not blank")
    @NotBlank(message = "Username field is required !!")
    @Size(min = 2, max = 20, message = "Username should be between 2 - 20 characters !!")
    @ApiModelProperty(notes = "The Login's username")
    private String username;

    @NotNull(message = "Password field should not blank")
    @NotBlank(message = "Password field is required !!")
    @Size(min = 2, message = "Password should be atleast 2 characters !!")
    @ApiModelProperty(notes = "The Login's Password")
    private String password;

    @Value("local")
    @Size(min = 2, max = 20, message = "Role should be between 2 - 20 characters !!")
    @NotEmpty(message = "Role should not be empty")
    @ApiModelProperty(notes = "The Login's Role")
    private String role;

}