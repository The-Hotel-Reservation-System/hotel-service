package com.example.hotel.controller;

import com.example.hotel.model.dto.HotelDto;
import com.example.hotel.model.request.CreateHotelRequest;
import com.example.hotel.model.request.UpdateHotelRequest;
import com.example.hotel.service.HotelService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
public class HotelControllerTest {

  public HotelController hotelController;
  public HotelService hotelService;

  @BeforeEach
  public void init() {
    hotelService = Mockito.mock(HotelService.class);
    hotelController = new HotelControllerImpl(hotelService);
  }

  @Test
  public void getHotelById_shouldWork() {
    Mockito.when(hotelService.getHotel(Mockito.any(String.class)))
        .thenReturn(HotelDto.builder().build().builder().build());
    ResponseEntity<HotelDto> responses = hotelController.getHotel(Mockito.any(String.class));
    Assertions.assertEquals(HttpStatus.OK, responses.getStatusCode());
  }

  @Test
  public void getAllHotel_shouldWork() {
    List<HotelDto> list = new ArrayList<>();
    HotelDto hotelDto = HotelDto.builder().build();
    list.add(hotelDto);
    Mockito.when(hotelService.getListHotel()).thenReturn(list);
    ResponseEntity<List<HotelDto>> responses = hotelController.getHotelList();
    Assertions.assertEquals(HttpStatus.OK, responses.getStatusCode());
  }

  @Test
  public void createGuest_shouldWork() {
    Mockito.when(hotelService.createHotel(Mockito.any(CreateHotelRequest.class)))
        .thenReturn(HotelDto.builder().build());
    ResponseEntity<HotelDto> responses = hotelController.createHotel(Mockito.any(CreateHotelRequest.class));
    Assertions.assertEquals(HttpStatus.OK, responses.getStatusCode());
  }

  @Test
  public void updateGuest_shouldWork() throws Exception {
    Mockito.when(hotelService.updateHotel(Mockito.any(String.class), Mockito.any(UpdateHotelRequest.class)))
        .thenReturn(HotelDto.builder().build());
    ResponseEntity<HotelDto> responses = hotelController.updateHotel(Mockito.any(String.class), Mockito.any(UpdateHotelRequest.class));
    Assertions.assertEquals(HttpStatus.OK, responses.getStatusCode());
  }

  @Test
  public void   deleteGuest_shouldWork() {
    Mockito.when(hotelService.deleteHotel(Mockito.any(String.class)))
        .thenReturn(Boolean.TRUE);
    ResponseEntity<Boolean> responses = hotelController.deleteHotel(Mockito.any(String.class));
    Assertions.assertEquals(HttpStatus.OK, responses.getStatusCode());
  }

}
