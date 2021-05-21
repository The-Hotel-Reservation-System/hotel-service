package com.example.hotel.exception;

import lombok.Getter;

public class HotelServiceException extends RuntimeException {

  @Getter
  HotelExceptionResponse hotelExceptionResponse;

  public HotelServiceException(HotelExceptionResponse hotelExceptionResponse) {
    this.hotelExceptionResponse = hotelExceptionResponse;
  }

}
