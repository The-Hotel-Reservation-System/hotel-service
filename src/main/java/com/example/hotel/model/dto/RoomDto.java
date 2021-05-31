package com.example.hotel.model.dto;

import com.example.hotel.model.entity.Hotel;
import com.example.hotel.model.entity.RoomStatus;
import com.example.hotel.model.entity.RoomType;
import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;

@Builder
@Data
public class RoomDto {
  private BigInteger id;
  private String type;
  private String status;
  private String hotel;
  private String price;
}
