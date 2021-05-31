package com.example.hotel.model.dto;

import com.example.hotel.model.entity.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class HotelDto {
  private String id;
  private String name;
  private String phone;
  private String address;
  private List<RoomDto> rooms;
}
