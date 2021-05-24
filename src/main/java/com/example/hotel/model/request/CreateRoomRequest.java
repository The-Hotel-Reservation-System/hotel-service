package com.example.hotel.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class CreateRoomRequest {
  private String hotelId;
  private String roomType;
  private String roomStatus;
}
