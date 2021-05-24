package com.example.hotel.controller;

import com.example.hotel.model.dto.RoomDto;
import com.example.hotel.model.request.CreateHotelRequest;
import com.example.hotel.model.request.UpdateHotelRequest;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import com.example.hotel.model.dto.HotelDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import javax.validation.Valid;

@RequestMapping("/api/hotel")
public interface HotelController {

  /**
   * Get the hotel with specific id
   * @param hotelId the id of hotel
   * @return HotelDto the hotel response model {@link HotelDto}
   */
  @GetMapping("/{hotelId}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Found hotel.",
          content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = HotelDto.class)) }),
      @ApiResponse(responseCode = "400", description = "Invalid id supplied.",
          content = @Content),
      @ApiResponse(responseCode = "404", description = "Hotel not found.",
          content = @Content)
  })
  ResponseEntity<HotelDto> getHotel(@PathVariable String hotelId);

  /**
   * Get all the hotels
   * @return List<HotelDto> list of hotel {@link HotelDto}
   */
  @GetMapping
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "List of hotel",
          content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = HotelDto.class)) }),
      @ApiResponse(responseCode = "400", description = "Invalid id supplied.",
          content = @Content)
  })
  ResponseEntity<List<HotelDto>> getHotelList();

  /**
   * Create a new hotel
   * @param request request create a hotel {@link CreateHotelRequest}
   * @return HotelDto the hotel response model {@link HotelDto}
   */
  @PostMapping
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Hotel is created.",
          content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = HotelDto.class)) }),
      @ApiResponse(responseCode = "400", description = "Failed to create hotel.",
          content = @Content)
  })
  ResponseEntity<HotelDto> createHotel(@Valid @RequestBody CreateHotelRequest request);

  /**
   *
   * @param hotelId the id of hotel
   * @param request hotel's update request {@link UpdateHotelRequest}
   * @return HotelDto the hotel response model {@link HotelDto}
   */
  @PutMapping("/{hotelId}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Hotel is updated.",
          content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = HotelDto.class)) }),
      @ApiResponse(responseCode = "400", description = "Failed to update hotel.",
          content = @Content)
  })
  ResponseEntity<HotelDto> updateHotel(@PathVariable String hotelId,@Valid @RequestBody UpdateHotelRequest request);

  /**
   *
   * @param hotelId the id of hotel
   * @return Boolean
   */
  @DeleteMapping("/{hotelId}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Hotel is deleted.",
          content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = HotelDto.class)) }),
      @ApiResponse(responseCode = "400", description = "Failed to delete hotel.",
          content = @Content)
  })
  ResponseEntity<Boolean> deleteHotel(@PathVariable String hotelId);

  /**
   * Get the room with specific id
   * @param hotelId the id of hotel
   * @return RoomDto the hotel response model {@link RoomDto}
   */
  @GetMapping("/{hotelId}/room")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Found room.",
          content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = RoomDto.class)) }),
      @ApiResponse(responseCode = "400", description = "Invalid id supplied.",
          content = @Content),
      @ApiResponse(responseCode = "404", description = "Room not found.",
          content = @Content)
  })
  ResponseEntity<List<RoomDto>> getRoom(@PathVariable String hotelId);
}
