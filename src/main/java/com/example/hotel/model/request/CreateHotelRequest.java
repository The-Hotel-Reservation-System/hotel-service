package com.example.hotel.model.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Builder
@Data
public class CreateHotelRequest {
  @NotBlank(message = "name should not be blank.")
  private String name;
  @NotBlank(message = "address should not be blank.")
  private String address;
  @NotBlank(message = "phone should not be blank.")
  private String phone;
}
