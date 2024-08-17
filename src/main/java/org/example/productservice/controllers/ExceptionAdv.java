package org.example.productservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

//@ControllerAdvice
public class ExceptionAdv {
    //@ExceptionHandler({Exception.class})
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>("Such toh phat hai", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
