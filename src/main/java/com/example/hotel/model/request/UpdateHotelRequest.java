package com.example.hotel.model.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class UpdateHotelRequest {
  private String name;
  private String address;
  private String phone;
}
