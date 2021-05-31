package com.example.hotel.service;

import com.example.hotel.exception.HotelServiceException;
import com.example.hotel.model.dto.RoomDto;
import com.example.hotel.model.entity.Hotel;
import com.example.hotel.model.entity.Room;
import com.example.hotel.model.entity.RoomStatus;
import com.example.hotel.model.entity.RoomType;
import com.example.hotel.model.request.CreateRoomRequest;
import com.example.hotel.model.request.UpdateRoomRequest;
import com.example.hotel.repository.HotelRepository;
import com.example.hotel.repository.RoomRepository;
import com.example.hotel.repository.RoomStatusRepository;
import com.example.hotel.repository.RoomTypeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigInteger;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class RoomServiceTest {

  private final String NAME = "Name";
  private final String ADDRESS = "Address";
  private final String PHONE = "1234567890";
  private final String PRICE = "12345";
  private final BigInteger ID = BigInteger.ONE;
  private final String ROOM_ID = "1";
  private final RoomStatus ROOM_STATUS =
      RoomStatus.builder()
          .id(1)
          .name(NAME)
          .build();
  private final RoomType ROOM_TYPE =
      RoomType.builder()
          .id(1)
          .name(NAME)
          .price(PRICE)
          .build();

  private RoomService roomService;
  @Mock
  private RoomRepository roomRepository;
  @Mock
  private HotelRepository hotelRepository;
  @Mock
  private RoomTypeRepository roomTypeRepository;
  @Mock
  private RoomStatusRepository roomStatusRepository;

  private Room room;
  private RoomDto roomDto;
  private Hotel hotel;
  private CreateRoomRequest createRoomRequest;
  private UpdateRoomRequest updateRoomRequest;

  @BeforeEach
  void init() {
    roomService = new RoomServiceImpl(roomRepository, hotelRepository, roomTypeRepository, roomStatusRepository);
    hotel = buildHotelModel();
    room = buildRoomModel();
    createRoomRequest = buildCreateRoomRequest();
    updateRoomRequest = buildUpdateRoomRequest();
  }

  @Test
  void getRoomById_shouldWork() {
    Mockito.when(roomRepository.findById(Mockito.any(BigInteger.class))).thenReturn(Optional.of(room));
    RoomDto roomDto = roomService.getRoom("1");
    Assertions.assertEquals(buildRoomDtoModel(), roomDto);
  }

  @Test
  void getRoomById_RoomNotFound_shouldThrowException() {
    Mockito.when(roomRepository.findById(Mockito.any(BigInteger.class))).thenReturn(Optional.empty());
    Assertions.assertThrows(HotelServiceException.class, () -> roomService.getRoom("1"));
  }

  @Test
  void createRoom_shouldWork() {
    Mockito.when(roomTypeRepository.findByName(Mockito.any(String.class))).thenReturn(Optional.of(ROOM_TYPE));
    Mockito.when(roomStatusRepository.findByName(Mockito.any(String.class))).thenReturn(Optional.of(ROOM_STATUS));
    Mockito.when(hotelRepository.findById(Mockito.any(BigInteger.class))).thenReturn(Optional.of(hotel));
    Mockito.when(roomRepository.save(Mockito.any(Room.class))).thenReturn(room);
    RoomDto roomDto = roomService.createHotel(buildCreateRoomRequest());
    Assertions.assertEquals(buildRoomDtoModel(), roomDto);
  }

  @Test
  void createRoom_RoomStatusNotFound_shouldThrowException() {
    Mockito.when(roomStatusRepository.findByName(Mockito.any(String.class))).thenReturn(Optional.empty());
    Assertions.assertThrows(HotelServiceException.class, () -> roomService.createHotel(createRoomRequest));
  }

  @Test
  void createRoom_RoomTypeNotFound_shouldThrowException() {
    Mockito.when(roomTypeRepository.findByName(Mockito.any(String.class))).thenReturn(Optional.empty());
    Mockito.when(roomStatusRepository.findByName(Mockito.any(String.class))).thenReturn(Optional.of(ROOM_STATUS));
    Assertions.assertThrows(HotelServiceException.class, () -> roomService.createHotel(createRoomRequest));
  }

  @Test
  void createRoom_HotelNotFound_shouldThrowException() {
    Mockito.when(roomTypeRepository.findByName(Mockito.any(String.class))).thenReturn(Optional.of(ROOM_TYPE));
    Mockito.when(roomStatusRepository.findByName(Mockito.any(String.class))).thenReturn(Optional.of(ROOM_STATUS));
    Mockito.when(hotelRepository.findById(Mockito.any(BigInteger.class))).thenReturn(Optional.empty());
    Assertions.assertThrows(HotelServiceException.class, () -> roomService.createHotel(createRoomRequest));
  }

  @Test
  void updateRoom_shouldWork() {
    Mockito.when(roomRepository.findById(Mockito.any(BigInteger.class))).thenReturn(Optional.of(room));
    Mockito.when(roomTypeRepository.findByName(Mockito.any(String.class))).thenReturn(Optional.of(ROOM_TYPE));
    Mockito.when(roomStatusRepository.findByName(Mockito.any(String.class))).thenReturn(Optional.of(ROOM_STATUS));
    Mockito.when(roomRepository.save(Mockito.any(Room.class))).thenReturn(room);
    RoomDto roomDto = roomService.updateRoom(ID.toString() ,buildUpdateRoomRequest());
    Assertions.assertEquals(buildRoomDtoModel(), roomDto);
  }

  @Test
  void updateRoom_RoomNotFound_shouldThrowException() {
    Mockito.when(roomTypeRepository.findByName(Mockito.any(String.class))).thenReturn(Optional.of(ROOM_TYPE));
    Mockito.when(roomStatusRepository.findByName(Mockito.any(String.class))).thenReturn(Optional.of(ROOM_STATUS));
    Mockito.when(roomRepository.findById(Mockito.any(BigInteger.class))).thenReturn(Optional.empty());
    Assertions.assertThrows(HotelServiceException.class, () -> roomService.updateRoom(ROOM_ID ,updateRoomRequest));
  }

  @Test
  void updateRoom_RoomStatusNotFound_shouldThrowException() {
    Mockito.when(roomStatusRepository.findByName(Mockito.any(String.class))).thenReturn(Optional.empty());
    Assertions.assertThrows(HotelServiceException.class, () -> roomService.updateRoom(ROOM_ID ,updateRoomRequest));
  }

  @Test
  void updateRoom_RoomTypeNotFound_shouldThrowException() {
    Mockito.when(roomTypeRepository.findByName(Mockito.any(String.class))).thenReturn(Optional.empty());
    Mockito.when(roomStatusRepository.findByName(Mockito.any(String.class))).thenReturn(Optional.of(ROOM_STATUS));
    Assertions.assertThrows(HotelServiceException.class, () -> roomService.updateRoom(ROOM_ID ,updateRoomRequest));
  }

  @Test
  void deleteRoom_shouldWork() {
    Mockito.when(roomRepository.findById(Mockito.any(BigInteger.class))).thenReturn(Optional.of(room));
    Mockito.doNothing().when(roomRepository).delete(Mockito.any(Room.class));
    Boolean result = roomService.deleteRoom(ID.toString());
    Assertions.assertEquals(Boolean.TRUE, result);
  }

  @Test
  void deleteRoom_RoomNotFound_shouldWork() {
    Mockito.when(roomRepository.findById(Mockito.any(BigInteger.class))).thenReturn(Optional.empty());
    Assertions.assertThrows(HotelServiceException.class, () -> roomService.deleteRoom(ROOM_ID));
  }

  private UpdateRoomRequest buildUpdateRoomRequest() {
    return UpdateRoomRequest.builder()
        .roomStatus(ROOM_STATUS.getName())
        .roomType(ROOM_TYPE.getName())
        .build();
  }

  private CreateRoomRequest buildCreateRoomRequest() {
    return CreateRoomRequest.builder()
        .hotelId(ID.toString())
        .roomStatus(ROOM_STATUS.getName())
        .roomType(ROOM_TYPE.getName())
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

  private Room buildRoomModel() {
    return Room.builder()
        .hotel(hotel)
        .id(ID)
        .status(ROOM_STATUS)
        .type(ROOM_TYPE)
        .build();
  }

  private RoomDto buildRoomDtoModel() {
    return RoomDto.builder()
        .hotel(hotel.getName())
        .id(ID)
        .status(ROOM_STATUS.getName())
        .type(ROOM_TYPE.getName())
        .price(PRICE)
        .build();
  }

}
