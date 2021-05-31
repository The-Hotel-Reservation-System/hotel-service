package com.example.hotel.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "room", schema = "public")
public class Room {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private BigInteger id;
  @ManyToOne(targetEntity = RoomType.class)
  @JoinColumn(name = "type_id")
  private RoomType type;
  @ManyToOne(targetEntity = RoomStatus.class)
  @JoinColumn(name = "status_id")
  private RoomStatus status;
  @ManyToOne(targetEntity = Hotel.class)
  @JoinColumn(name = "hotel_id")
  private Hotel hotel;
}
