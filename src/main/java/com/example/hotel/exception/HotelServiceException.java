package com.example.hotel.exception;

import lombok.Getter;

public class HotelServiceException extends RuntimeException {

  HotelExceptionResponse hotelExceptionResponse;

  public HotelServiceException(HotelExceptionResponse hotelExceptionResponse) {
    this.hotelExceptionResponse = hotelExceptionResponse;
  }

  public HotelExceptionResponse getHotelExceptionResponse() {
    return hotelExceptionResponse;
  }
}
