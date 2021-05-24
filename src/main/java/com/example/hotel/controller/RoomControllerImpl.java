package com.example.hotel.controller;

import com.example.hotel.model.dto.RoomDto;
import com.example.hotel.model.request.CreateRoomRequest;
import com.example.hotel.model.request.UpdateRoomRequest;
import com.example.hotel.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class RoomControllerImpl implements RoomController {

  private RoomService roomService;

  public RoomControllerImpl(RoomService roomService) {
    this.roomService = roomService;
  }

  @Override
  public ResponseEntity<RoomDto> getRoom(String roomId) {
    return ResponseEntity.ok(roomService.getRoom(roomId));
  }

  @Override
  public ResponseEntity<RoomDto> createRoom(@Valid CreateRoomRequest request) {
    return ResponseEntity.ok(roomService.createHotel(request));
  }

  @Override
  public ResponseEntity<RoomDto> updateRoom(String roomId, @Valid UpdateRoomRequest request) {
    return ResponseEntity.ok(roomService.updateRoom(roomId, request));
  }

  @Override
  public ResponseEntity<Boolean> deleteRoom(String roomId) {
    return ResponseEntity.ok(roomService.deleteRoom(roomId));
  }

}
