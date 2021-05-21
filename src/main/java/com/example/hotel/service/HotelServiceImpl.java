package com.example.hotel.service;

import com.example.hotel.exception.HotelExceptionResponse;
import com.example.hotel.exception.HotelServiceException;
import com.example.hotel.model.dto.HotelDto;
import com.example.hotel.model.entity.Hotel;
import com.example.hotel.model.request.CreateHotelRequest;
import com.example.hotel.model.request.UpdateHotelRequest;
import com.example.hotel.repository.HotelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class HotelServiceImpl implements HotelService {

  private HotelRepository hotelRepository;

  public HotelServiceImpl(HotelRepository hotelRepository) {
    this.hotelRepository = hotelRepository;
  }

  @Override
  public HotelDto getHotel(String hotelId) {
    log.info("Get a hotel with id {}", hotelId);
    return hotelRepository.findById(new BigInteger(hotelId))
        .map(hotel -> convertToHotelDto(hotel))
        .orElseThrow(() -> {
          log.error("Not found hotel with id {}", hotelId);
          return new HotelServiceException(HotelExceptionResponse.HOTEL_NOT_FOUND);
        });
  }

  @Override
  public List<HotelDto> getListHotel() {
    log.info("Get all the hotels");
    List<HotelDto> list = new ArrayList<>();
    hotelRepository.findAll().forEach(hotel -> list.add(convertToHotelDto(hotel)));
    return list;
  }

  @Override
  public HotelDto createHotel(CreateHotelRequest request) {
    log.info("Create hotel with request {}", request);
    Hotel hotel = Hotel.builder()
        .name(request.getName())
        .phone(request.getPhone())
        .address(request.getAddress())
        .build();
    return convertToHotelDto(hotelRepository.save(hotel));
  }

  @Override
  public HotelDto updateHotel(String hotelId, UpdateHotelRequest request) {
    log.info("Update the hotel {} with request {}", hotelId, request);
    return hotelRepository.findById(new BigInteger(hotelId))
        .map(hotel -> {
          hotel.setAddress(Optional.ofNullable(request.getAddress()).orElse(hotel.getAddress()));
          hotel.setName(Optional.ofNullable(request.getName()).orElse(hotel.getName()));
          hotel.setPhone(Optional.ofNullable(request.getPhone()).orElse(hotel.getPhone()));
          return convertToHotelDto(hotelRepository.save(hotel));
        })
        .orElseThrow(() -> {
          log.error("Cannot find hotel with id {} to update", hotelId);
          return new HotelServiceException(HotelExceptionResponse.HOTEL_NOT_FOUND);
        });
  }

  @Override
  public Boolean deleteHotel(String hotelId) {
    log.info("Delete hotel with id {}", hotelId);
    return hotelRepository.findById(new BigInteger(hotelId))
        .map(hotel -> {
          hotelRepository.delete(hotel);
          return Boolean.TRUE;
        })
        .orElseThrow(() -> {
          log.error("Cannot find hotel {} to delete", hotelId);
          return new HotelServiceException(HotelExceptionResponse.HOTEL_NOT_FOUND);
        });
  }

  private HotelDto convertToHotelDto(Hotel hotel) {
    return HotelDto.builder()
        .id(hotel.getId())
        .address(hotel.getAddress())
        .phone(hotel.getPhone())
        .name(hotel.getName())
        .build();
  }
}
