package com.pragma.brewery.web;

import com.pragma.brewery.service.TemperatureDataNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.MultiValueMap;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.MediaType.TEXT_HTML_VALUE;

/**
 * Created by ranjeethpt on 29/10/17.
 *
 * @author ranjeethpt
 */
@Slf4j
@RestControllerAdvice
@Profile("!unitTest")
public class GlobalExceptionHandler {

    @ExceptionHandler(TemperatureDataNotFoundException.class)
    public ResponseEntity<String> temperatureDataNotFound(HttpServletRequest req, Exception exception) {
        log.warn("Exception for request {}, exception is {}", req, exception);
        MultiValueMap<String, String> header = new HttpHeaders();
        header.add("Content-Type", TEXT_HTML_VALUE);
        return new ResponseEntity<>(exception.getMessage(), header, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentNotValidException.class,
            HttpRequestMethodNotSupportedException.class, HttpMediaTypeNotSupportedException.class})
    public ResponseEntity<String> badRequest(HttpServletRequest req, Exception exception) {
        log.warn("Exception for request {}, exception is {}", req, exception);
        return new ResponseEntity<>("Bad request. Supported API call is GET Content type 'application/json' /services/temperature/{id}.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> serverErrorRequest(HttpServletRequest req, Exception exception) {
        log.error("Exception for request {}, exception is {}", req, exception);
        return new ResponseEntity<>("Unknown error has occurred. Please contact support. ", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}