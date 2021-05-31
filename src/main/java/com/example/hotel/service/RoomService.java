package com.example.hotel.service;

import com.example.hotel.model.dto.RoomDto;
import com.example.hotel.model.request.CreateRoomRequest;
import com.example.hotel.model.request.UpdateRoomRequest;

public interface RoomService {

  /**
   *
   * @param roomId the id of room
   * @return RoomDto the room response model {@link RoomDto}
   */
  RoomDto getRoom(String roomId);
  /**
   * Create a new room
   * @param request request create a room {@link CreateRoomRequest}
   * @return RoomDto the room response model {@link RoomDto}
   */
  RoomDto createHotel(CreateRoomRequest request);

  /**
   * Update an existing hotel
   * @param roomId the id of room
   * @param request room's update request {@link UpdateRoomRequest}
   * @return RoomDto the room response model {@link RoomDto}
   */
  RoomDto updateRoom(String roomId, UpdateRoomRequest request);

  /**
   * Delete an exiting room
   * @param roomId the id of room
   * @return Boolean
   */
  Boolean deleteRoom(String roomId);
}
