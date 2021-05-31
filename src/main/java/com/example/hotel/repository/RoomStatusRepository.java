package com.example.hotel.repository;

import com.example.hotel.model.entity.RoomStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomStatusRepository extends CrudRepository<RoomStatus, Integer> {

  Optional<RoomStatus> findByName(String status);
}
