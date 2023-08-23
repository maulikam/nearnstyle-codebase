package com.nearnstyle.apis.common.exception;

import org.apache.catalina.connector.ClientAbortException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Level;
import java.util.logging.Logger;

@ControllerAdvice
public class GlobalExceptionHandlers {

    private static final Logger LOGGER = Logger.getLogger(GlobalExceptionHandlers.class.getName());

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<NearNStyleResponseEntity> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(new NearNStyleResponseEntity(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public void handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        LOGGER.log(Level.INFO, e.getMessage(), e);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NearNStyleUserException.class)
    public ResponseEntity<NearNStyleResponseEntity> handleNearNStyleUserException(NearNStyleUserException e) {
        if (e != null) {
            NearNStyleResponseEntity rE = e.getResponse();
            return new ResponseEntity<>(rE, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({ DataIntegrityViolationException.class, IllegalArgumentException.class, Exception.class })
    public ResponseEntity<NearNStyleResponseEntity> handleAllExceptions(HttpServletRequest request, Exception e) {
        if (!(e instanceof NearNStyleUserException) && !(e instanceof ClientAbortException)) {
            // emailUtil.sendExceptionEmail(e, null, request, "");
            LOGGER.log(Level.INFO, e.getMessage(), e);
        }

        if (e instanceof DataIntegrityViolationException) {
            return new ResponseEntity<>(new NearNStyleResponseEntity("Data integrity violation."), HttpStatus.CONFLICT);
        } else if (e instanceof IllegalArgumentException) {
            return new ResponseEntity<>(new NearNStyleResponseEntity("Bad request."), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(new NearNStyleResponseEntity("Internal server error."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
