package com.example.hotel.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum HotelExceptionResponse {

  UNHANDLED_EXCEPTION(HttpStatus.BAD_REQUEST, "2000", "Unhandled exception."),
  HOTEL_NOT_FOUND(HttpStatus.NOT_FOUND, "2001", "Hotel is not found."),
  INVALID_ARGUMENT(HttpStatus.BAD_REQUEST, "2002", ""),
  ROOM_NOT_FOUND(HttpStatus.NOT_FOUND, "2003", "Room is not found."),
  ROOM_STATUS_NOT_FOUND(HttpStatus.NOT_FOUND, "2004", "Room status is not found."),
  ROOM_TYPE_NOT_FOUND(HttpStatus.NOT_FOUND, "2005", "Room type is not found.");

  HotelExceptionResponse(HttpStatus httpStatus, String errorCode, String errorMessage) {
    this.httpStatus = httpStatus;
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }

  private HttpStatus httpStatus;
  private String errorCode;
  private String errorMessage;
}
