package com.project.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author 55683
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private long userID;

    @NotNull(message = "Username field should not blank")
    @NotBlank(message = "Username field is required !!")
    @Size(min = 2, max = 20, message = "Username should be between 2 - 20 characters !!")
    private String username;

    @NotNull(message = "Password field should not blank")
    @NotBlank(message = "Password field is required !!")
    @Size(min = 2, message = "Password should be atleast 2 characters !!")
    private String password;

    @NotNull(message = "Address field should not blank")
    @NotBlank(message = "Address field is required !!")
    @Size(min = 2, max = 20, message = "Address should be between 2 - 20 characters !!")
    private String address;

    @NotNull(message = "Company field should not blank")
    @NotBlank(message = "Company field is required !!")
    @Size(min = 2, max = 20, message = "Company should be between 2 - 20 characters !!")
    private String company;

    @Value("local")
    @Size(min = 2, max = 20, message = "Role should be between 2 - 20 characters !!")
    @NotEmpty(message = "Role should not be empty")
    private String role;

    @NotNull(message = "OfficeID field should not blank")
    @NotBlank(message = "OfficeID field is required !!")
    @Size(min = 2, max = 20, message = "OfficeID should be between 2 - 20 characters !!")
    private String officeID;

}