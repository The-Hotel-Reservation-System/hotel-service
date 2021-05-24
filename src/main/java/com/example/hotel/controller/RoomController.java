package com.example.hotel.controller;

import com.example.hotel.model.dto.HotelDto;
import com.example.hotel.model.dto.RoomDto;
import com.example.hotel.model.request.CreateRoomRequest;
import com.example.hotel.model.request.UpdateRoomRequest;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/api/room")
public interface RoomController {

  @GetMapping("/{roomId}")
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "Get room",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = RoomDto.class))}),
      @ApiResponse(
          responseCode = "400",
          description = "Invalid id supplied.",
          content = @Content)
  })
  ResponseEntity<RoomDto> getRoom(@PathVariable String roomId);

  @PostMapping
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "201",
          description = "Room is created",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = RoomDto.class))}),
      @ApiResponse(
          responseCode = "400",
          description = "Invalid id supplied.",
          content = @Content)
  })
  ResponseEntity<RoomDto> createRoom(@Valid @RequestBody CreateRoomRequest request);

  @PutMapping("/{roomId}")
  @ApiResponses(value = {
      @ApiResponse(
          responseCode = "200",
          description = "Room is updated",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = RoomDto.class))}),
      @ApiResponse(
          responseCode = "400",
          description = "Invalid id supplied.",
          content = @Content)
  })
  ResponseEntity<RoomDto> updateRoom(@PathVariable String roomId, @Valid @RequestBody UpdateRoomRequest request);

  @DeleteMapping("/{roomId}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Room is deleted.",
          content = { @Content(mediaType = "application/json",
              schema = @Schema(implementation = HotelDto.class)) }),
      @ApiResponse(responseCode = "400", description = "Failed to delete room.",
          content = @Content)
  })
  ResponseEntity<Boolean> deleteRoom(@PathVariable String roomId);
}
