package com.gl.rewardservice.utility;


import com.gl.rewardservice.exception.RewardAlreadyAssignedException;
import com.gl.rewardservice.exception.RewardNotFoundException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerException {
    private static final Log LOGGER= LogFactory.getLog(ControllerException.class);
    @ExceptionHandler(RewardNotFoundException.class)
    public ResponseEntity<Error> customException(RewardNotFoundException ex){
        Error error=new Error();
        List<String> errMessage=new ArrayList<>();
        errMessage.add(ex.getMessage());
        error.setMessage(errMessage);
        error.setStatusCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
        error.setTimeStamp(LocalDateTime.now());
        LOGGER.error(ex);
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(RewardAlreadyAssignedException.class)
    public ResponseEntity<Error> Exception(RewardAlreadyAssignedException ex){
        Error error=new Error();
        List<String> errMessage=new ArrayList<>();
        errMessage.add(ex.getMessage());
        error.setMessage(errMessage);
        error.setStatusCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
        error.setTimeStamp(LocalDateTime.now());
        LOGGER.error(ex);
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> generalException(Exception ex){
        Error error=new Error();
        List<String> errMessage=new ArrayList<>();
        errMessage.add(ex.getMessage());
        error.setMessage(errMessage);
        error.setStatusCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        error.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> validationException(MethodArgumentNotValidException ex){
        Error error=new Error();
        List<String> errMessage=new ArrayList<>();
        errMessage.addAll(ex.getBindingResult().getAllErrors().stream().map(e->"Request Body:" +e.getDefaultMessage()).collect(Collectors.toList()));
        error.setMessage(errMessage);
        error.setStatusCode(String.valueOf(HttpStatus.BAD_REQUEST.value()));
        error.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Error> validationException(ConstraintViolationException ex){
        Error error=new Error();
        List<String> errMessage=new ArrayList<>();
        errMessage.addAll(ex.getConstraintName().lines().collect(Collectors.toList()));
        error.setMessage(errMessage);
        error.setStatusCode(String.valueOf(HttpStatus.NOT_FOUND.value()));
        error.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);


    }
}