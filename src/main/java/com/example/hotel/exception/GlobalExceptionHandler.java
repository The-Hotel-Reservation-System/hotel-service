package com.example.hotel.exception;

import brave.Tracer;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.apache.commons.lang3.StringUtils;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @Autowired
  HotelServiceExceptionConverter converter;

  @Autowired
  private Tracer tracer;

  @ExceptionHandler(HotelServiceException.class)
  public ResponseEntity<Object> handleHotelException(HotelServiceException exception) {
    log.error("HotelException", exception);
    return new ResponseEntity<>(
        converter.toJsonNode(exception.getHotelExceptionResponse(), StringUtils.EMPTY, tracer),
        new HttpHeaders(),
        exception.hotelExceptionResponse.getHttpStatus()
    );
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> handleInvalidArgumentException(
      MethodArgumentNotValidException exception)
      throws JsonProcessingException {
    log.error("MethodArgumentNotValidException", exception);
    return new ResponseEntity<>(
        converter.toJsonNode(HotelExceptionResponse.INVALID_ARGUMENT,
                             exception
                                 .getBindingResult()
                                 .getAllErrors()
                                 .get(NumberUtils.INTEGER_ZERO)
                                 .getDefaultMessage(),
                             tracer
        ),
        new HttpHeaders(),
        HttpStatus.BAD_REQUEST);
  }

}
