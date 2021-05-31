package com.example.hotel.service;

import com.example.hotel.model.dto.HotelDto;
import com.example.hotel.model.dto.RoomDto;
import com.example.hotel.model.request.CreateHotelRequest;
import com.example.hotel.model.request.UpdateHotelRequest;

import java.util.List;

public interface HotelService {

  /**
   * Get the hotel with specific id
   * @param hotelId the id of hotel
   * @return HotelDto the hotel response model {@link HotelDto}
   */
  HotelDto getHotel(String hotelId);

  /**
   * Get all the hotels
   * @return List<HotelDto> list of hotel {@link HotelDto}
   */
  List<HotelDto> getListHotel();

  /**
   * Create a new hotel
   * @param request request create a hotel {@link CreateHotelRequest}
   * @return HotelDto the hotel response model {@link HotelDto}
   */
  HotelDto createHotel(CreateHotelRequest request);

  /**
   * Update an existing hotel
   * @param hotelId the id of hotel
   * @param request hotel's update request {@link UpdateHotelRequest}
   * @return HotelDto the hotel response model {@link HotelDto}
   */
  HotelDto updateHotel(String hotelId, UpdateHotelRequest request);

  /**
   * Delete an exiting hotel
   * @param hotelId the id of hotel
   * @return Boolean
   */
  Boolean deleteHotel(String hotelId);

  /**
   *
   * @param hotelId hotel id
   * @return List<RoomDto> the rooms of hotel {@link List<RoomDto>}
   */
  List<RoomDto> getRoom(String hotelId);
}
