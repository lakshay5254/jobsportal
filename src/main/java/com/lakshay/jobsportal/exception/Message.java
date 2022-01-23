package com.lakshay.jobsportal.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Message {
    private int statusCode;
    private LocalDateTime timestamp;
    private String message;
}