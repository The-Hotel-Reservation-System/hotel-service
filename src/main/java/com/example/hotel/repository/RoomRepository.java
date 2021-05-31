package com.example.hotel.repository;

import com.example.hotel.model.entity.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface RoomRepository extends CrudRepository<Room, BigInteger> {

}
