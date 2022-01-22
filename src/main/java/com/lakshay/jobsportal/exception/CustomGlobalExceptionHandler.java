package com.lakshay.jobsportal.exception;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toCollection;

@RestControllerAdvice
@Slf4j
public class CustomGlobalExceptionHandler  extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<Message> businessException(BusinessException ex) {
        Message message = new Message(
                Integer.parseInt(ex.getErrorCode()),
                LocalDateTime.now(),
                ex.getErrorMessage());
        return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        Multimap<String, String> errors = ArrayListMultimap.create();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {

            errors.put(error.getField(), error.getDefaultMessage());
        }
        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.put(error.getObjectName(),error.getDefaultMessage());
        }
//        Map<String, List<String>> maperrors = (Map<String, List<String>>)(Map<?, ?>)errors.asMap();
        List<ValidationError> validationErrors=new ArrayList<>();
        errors.asMap().forEach((k,v)->validationErrors.add(new ValidationError(k,v.stream().collect(toCollection(ArrayList::new)))));



        logger.info(validationErrors);
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<Message> badCredentials(BadCredentialsException ex) {
        Message message = new Message(
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                ex.getMessage());

        return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Message> handleAllExceptions(Exception ex, WebRequest request) {
        Message message = new Message(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now(),
                ex.getMessage());

        return new ResponseEntity<Message>(message, HttpStatus.INTERNAL_SERVER_ERROR);

    }



}

