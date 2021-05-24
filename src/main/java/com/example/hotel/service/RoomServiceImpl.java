package com.example.hotel.service;

import com.example.hotel.exception.HotelExceptionResponse;
import com.example.hotel.exception.HotelServiceException;
import com.example.hotel.model.dto.RoomDto;
import com.example.hotel.model.entity.Room;
import com.example.hotel.model.request.CreateRoomRequest;
import com.example.hotel.model.request.UpdateRoomRequest;
import com.example.hotel.repository.HotelRepository;
import com.example.hotel.repository.RoomRepository;
import com.example.hotel.repository.RoomStatusRepository;
import com.example.hotel.repository.RoomTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
@Slf4j
public class RoomServiceImpl implements RoomService {

  private RoomRepository roomRepository;
  private HotelRepository hotelRepository;
  private RoomTypeRepository roomTypeRepository;
  private RoomStatusRepository roomStatusRepository;

  public RoomServiceImpl(RoomRepository roomRepository, HotelRepository hotelRepository,
                         RoomTypeRepository roomTypeRepository,
                         RoomStatusRepository roomStatusRepository) {
    this.roomRepository = roomRepository;
    this.hotelRepository = hotelRepository;
    this.roomTypeRepository = roomTypeRepository;
    this.roomStatusRepository = roomStatusRepository;
  }

  private RoomDto convertToRoomDto(Room room) {
      return RoomDto.builder()
          .hotel(room.getHotel().getName())
          .id(room.getId())
          .status(room.getStatus().getName())
          .type(room.getType().getName())
          .price(room.getType().getPrice()).build();
  }

  @Override
  public RoomDto getRoom(String roomId) {
    log.info("Get room {}", roomId);
    return roomRepository.findById(new BigInteger(roomId))
        .map(this::convertToRoomDto)
        .orElseThrow(() -> {
          log.error("Cannot find room {}", roomId);
          return new HotelServiceException(HotelExceptionResponse.ROOM_NOT_FOUND);
        });
  }

  @Override
  public RoomDto createHotel(CreateRoomRequest request) {
    log.info("Create room with request {}", request);
    var roomStatus = roomStatusRepository.findByName(request.getRoomStatus())
        .orElseThrow(() -> {
          log.error("Cannot find room status {}", request.getRoomStatus());
          return new HotelServiceException(HotelExceptionResponse.ROOM_STATUS_NOT_FOUND);
        });

    var roomType = roomTypeRepository.findByName(request.getRoomType())
        .orElseThrow(() -> {
          log.error("Cannot find room status {}", request.getRoomStatus());
          return new HotelServiceException(HotelExceptionResponse.ROOM_TYPE_NOT_FOUND);
        });

    var hotel = hotelRepository.findById(new BigInteger(request.getHotelId()))
        .orElseThrow(() -> {
          log.error("Cannot find hotel {}", request.getHotelId());
          return new HotelServiceException(HotelExceptionResponse.HOTEL_NOT_FOUND);
        });

    var room = Room.builder()
        .type(roomType)
        .status(roomStatus)
        .hotel(hotel)
        .build();
    return convertToRoomDto(roomRepository.save(room));
  }

  @Override
  public RoomDto updateRoom(String roomId, UpdateRoomRequest request) {
    log.info("Update room {} with request {}", roomId, request);

    var roomStatus = roomStatusRepository.findByName(request.getRoomStatus())
        .orElseThrow(() -> {
          log.error("Cannot find room status {}", request.getRoomStatus());
          return new HotelServiceException(HotelExceptionResponse.ROOM_STATUS_NOT_FOUND);
        });

    var roomType = roomTypeRepository.findByName(request.getRoomType())
        .orElseThrow(() -> {
          log.error("Cannot find room status {}", request.getRoomStatus());
          return new HotelServiceException(HotelExceptionResponse.ROOM_TYPE_NOT_FOUND);
        });

    return roomRepository.findById(new BigInteger(roomId))
        .map(room -> {
          room.setType(roomType);
          room.setStatus(roomStatus);
          return convertToRoomDto(roomRepository.save(room));
        })
        .orElseThrow(() -> {
          log.error("Cannot find a room {}", roomId);
          return new HotelServiceException(HotelExceptionResponse.ROOM_NOT_FOUND);
        });
  }

  @Override
  public Boolean deleteRoom(String roomId) {
    log.info("Delete room {}", roomId);
    return roomRepository.findById(new BigInteger(roomId))
        .map(room -> {
          roomRepository.delete(room);
          return Boolean.TRUE;
        })
        .orElseThrow(() -> {
          log.error("Cannot find room {}", roomId);
          return new HotelServiceException(HotelExceptionResponse.ROOM_NOT_FOUND);
        });
  }
}
