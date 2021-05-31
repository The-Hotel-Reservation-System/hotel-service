package com.example.hotel.controller;

import com.example.hotel.model.dto.HotelDto;
import com.example.hotel.model.dto.RoomDto;
import com.example.hotel.model.request.CreateHotelRequest;
import com.example.hotel.model.request.UpdateHotelRequest;
import com.example.hotel.service.HotelService;
import com.example.hotel.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import javax.validation.Valid;

@RestController
public class HotelControllerImpl implements HotelController {

  HotelService hotelService;

  public HotelControllerImpl(HotelService hotelService) {
    this.hotelService = hotelService;
  }


  @Override
  public ResponseEntity<HotelDto> getHotel(String hotelId) {
    return ResponseEntity.ok(hotelService.getHotel(hotelId));
  }

  @Override
  public ResponseEntity<List<HotelDto>> getHotelList() {
    return ResponseEntity.ok(hotelService.getListHotel());
  }

  @Override
  public ResponseEntity<HotelDto> createHotel(@Valid CreateHotelRequest request) {
    return ResponseEntity.ok(hotelService.createHotel(request));
  }

  @Override
  public ResponseEntity<HotelDto> updateHotel(String hotelId, @Valid UpdateHotelRequest request) {
    return ResponseEntity.ok(hotelService.updateHotel(hotelId, request));
  }

  @Override
  public ResponseEntity<Boolean> deleteHotel(String hotelId) {
    return ResponseEntity.ok(hotelService.deleteHotel(hotelId));
  }

  @Override
  public ResponseEntity<List<RoomDto>> getRoom(String hotelId) {
    return ResponseEntity.ok(hotelService.getRoom(hotelId));
  }
}
