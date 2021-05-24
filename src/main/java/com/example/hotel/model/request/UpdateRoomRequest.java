package com.example.hotel.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UpdateRoomRequest {
  private String roomType;
  private String roomStatus;
}
