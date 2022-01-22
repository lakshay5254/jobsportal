package com.lakshay.jobsportal.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationError {
    private String field;
    private List<String> errors;
}
