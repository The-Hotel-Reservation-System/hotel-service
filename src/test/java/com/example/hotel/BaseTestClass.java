package com.example.hotel;

import com.example.hotel.controller.HotelController;
import com.example.hotel.controller.RoomController;
import com.example.hotel.service.HotelService;
import com.example.hotel.service.RoomService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@DirtiesContext
@AutoConfigureMessageVerifier
public class BaseTestClass {

  @Autowired
  private HotelController hotelController;

  @Autowired
  private RoomController roomController;

  @Autowired
  private HotelService hotelService;

  @Autowired
  private RoomService roomService;

  @BeforeEach
  public void setup() {
    StandaloneMockMvcBuilder standaloneMockMvcBuilder
        = MockMvcBuilders.standaloneSetup(hotelController, roomController);
    RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder);

  }
}
