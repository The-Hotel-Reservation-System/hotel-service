package com.example.hotel.controller;

import com.example.hotel.model.dto.HotelDto;
import com.example.hotel.model.dto.RoomDto;
import com.example.hotel.model.request.CreateHotelRequest;
import com.example.hotel.model.request.CreateRoomRequest;
import com.example.hotel.model.request.UpdateHotelRequest;
import com.example.hotel.model.request.UpdateRoomRequest;
import com.example.hotel.service.RoomService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class RoomControllerTest {
  public RoomController roomController;
  public RoomService roomService;

  @BeforeEach
  public void init() {
    roomService = Mockito.mock(RoomService.class);
    roomController = new RoomControllerImpl(roomService);
  }

  @Test
  public void getHotelById_shouldWork() {
    Mockito.when(roomService.getRoom(Mockito.any(String.class)))
        .thenReturn(RoomDto.builder().build().builder().build());
    ResponseEntity<RoomDto> responses = roomController.getRoom(Mockito.any(String.class));
    Assertions.assertEquals(HttpStatus.OK, responses.getStatusCode());
  }

  @Test
  public void createRoom_shouldWork() {
    Mockito.when(roomService.createHotel(Mockito.any(CreateRoomRequest.class)))
        .thenReturn(RoomDto.builder().build());
    ResponseEntity<RoomDto> responses = roomController.createRoom(Mockito.any(CreateRoomRequest.class));
    Assertions.assertEquals(HttpStatus.OK, responses.getStatusCode());
  }

  @Test
  public void updateGuest_shouldWork() throws Exception {
    Mockito.when(roomService.updateRoom(Mockito.any(String.class), Mockito.any(UpdateRoomRequest.class)))
        .thenReturn(RoomDto.builder().build());
    ResponseEntity<RoomDto> responses = roomController
        .updateRoom(Mockito.any(String.class), Mockito.any(UpdateRoomRequest.class));
    Assertions.assertEquals(HttpStatus.OK, responses.getStatusCode());
  }

  @Test
  public void   deleteGuest_shouldWork() {
    Mockito.when(roomService.deleteRoom(Mockito.any(String.class)))
        .thenReturn(Boolean.TRUE);
    ResponseEntity<Boolean> responses = roomController.deleteRoom(Mockito.any(String.class));
    Assertions.assertEquals(HttpStatus.OK, responses.getStatusCode());
  }

}
