package com.lakshay.jobsportal.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupDto {
    @NotBlank(message = " Cant be blank")
    @Length(min = 4, max = 25)
    @Column(nullable = false,unique = true)
    private String username;


    //@Size(max = 10, min = 10, message = "Mobile number should be of 10 digits")
    @NotBlank(message = "cant be blank")
    @Pattern(regexp="(^[0-9]{10})", message = "Mobile number must be 10 digit ")
    private String mobile;


    @Column(nullable = false, unique = true)
    @NotBlank(message = "cant be blank")
    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "cant be blank")
    @Column(nullable = false)
    private String password;
}
