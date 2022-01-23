package com.lakshay.jobsportal.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Data
public class LoginRequest {
    @NotBlank(message = " Cant be blank")
    @Length(min = 4, max = 25)
    String username;
    @NotBlank(message = "cant be blank")
    @Column(nullable = false)
    String password;
}
