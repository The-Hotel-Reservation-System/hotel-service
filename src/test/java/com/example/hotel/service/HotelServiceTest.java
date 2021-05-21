package com.example.hotel.service;

import com.example.hotel.exception.HotelServiceException;
import com.example.hotel.model.dto.HotelDto;
import com.example.hotel.model.entity.Hotel;
import com.example.hotel.model.request.CreateHotelRequest;
import com.example.hotel.model.request.UpdateHotelRequest;
import com.example.hotel.repository.HotelRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class HotelServiceTest {

  private final String NAME = "Name";
  private final String ADDRESS = "Address";
  private final String PHONE = "1234567890";
  private final BigInteger ID = BigInteger.ONE;
  private Hotel hotel;

  @Mock
  HotelRepository hotelRepository;
  HotelService hotelService;
  List<HotelDto> hotelDtoList = new ArrayList<>();
  List<Hotel> hotelList = new ArrayList<>();

  @BeforeEach
  public void init() {
    hotel = buildHotelModel();
    hotelService = new HotelServiceImpl(hotelRepository);
    hotelDtoList.add(buildHotelDtoModel());
    hotelList.add(buildHotelModel());
  }

  @Test
  public void getHotel_shouldWork() {
    Mockito.when(hotelRepository.findById(Mockito.any(BigInteger.class))).thenReturn(Optional.of(hotel));
    HotelDto hotelDto = hotelService.getHotel("123");
    Assertions.assertEquals(NAME, hotelDto.getName());
    Assertions.assertEquals(ADDRESS, hotelDto.getAddress());
    Assertions.assertEquals(PHONE, hotelDto.getPhone());
    Assertions.assertEquals(ID, hotelDto.getId());
  }

  @Test
  public void getHotel_NotFound_shouldThrowException() {
    Mockito.when(hotelRepository.findById(Mockito.any(BigInteger.class))).thenReturn(Optional.empty());
    Assertions.assertThrows(HotelServiceException.class, () -> hotelService.getHotel("200"));
  }

  @Test
  public void getAllHotel_shouldWork() {
    Mockito.when(hotelRepository.findAll()).thenReturn(hotelList);
    List<HotelDto> list = hotelService.getListHotel();
    Assertions.assertEquals(hotelDtoList.get(0), list.get(0));
  }

  @Test
  public void createHotel_shouldWork() {
    Mockito.when(hotelRepository.save(Mockito.any(Hotel.class))).thenReturn(hotel);
    HotelDto result  = hotelService.createHotel(buildCreateHotelRequest());
    Assertions.assertEquals(buildHotelDtoModel(), result);
  }

  @Test
  public void updateHotel_shouldWork() throws Exception {
    Mockito.when(hotelRepository.save(Mockito.any(Hotel.class))).thenReturn(hotel);
    Mockito.when(hotelRepository.findById(Mockito.any(BigInteger.class))).thenReturn(Optional.of(hotel));
    HotelDto result = hotelService.updateHotel("12", buildUpdateHotelRequest());
    Assertions.assertEquals(buildHotelDtoModel(), result);
  }

  @Test
  public void updateHotel_NotFound_shouldThrowException() throws Exception {
    Mockito.when(hotelRepository.findById(Mockito.any(BigInteger.class))).thenReturn(Optional.empty());
    Assertions.assertThrows(HotelServiceException.class,
                            () -> hotelService.updateHotel("12", buildUpdateHotelRequest()));
  }

  @Test
  public void deleteHotel_shouldWork() {
    Mockito.when(hotelRepository.findById(Mockito.any(BigInteger.class))).thenReturn(Optional.of(hotel));
    Boolean result = hotelService.deleteHotel("123");
    Assertions.assertEquals(Boolean.TRUE, result);
  }

  @Test
  public void deleteHotel_NotFound_shouldWork() {
    Mockito.when(hotelRepository.findById(Mockito.any(BigInteger.class))).thenReturn(Optional.empty());
    Assertions.assertThrows(HotelServiceException.class, () -> hotelService.deleteHotel("12"));
  }

  private UpdateHotelRequest buildUpdateHotelRequest() {
    return UpdateHotelRequest.builder()
        .address(ADDRESS)
        .name(NAME)
        .phone(PHONE)
        .build();
  }

  private CreateHotelRequest buildCreateHotelRequest() {
    return CreateHotelRequest.builder()
        .address(ADDRESS)
        .name(NAME)
        .phone(PHONE)
        .build();
  }

  private Hotel buildHotelModel() {
    return Hotel.builder()
        .address(ADDRESS)
        .phone(PHONE)
        .name(NAME)
        .id(ID)
        .build();
  }

  private HotelDto buildHotelDtoModel() {
    return HotelDto.builder()
        .address(ADDRESS)
        .phone(PHONE)
        .name(NAME)
        .id(ID)
        .build();
  }
}
