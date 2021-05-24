package com.example.hotel.repository;

import com.example.hotel.model.entity.RoomType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomTypeRepository extends CrudRepository<RoomType, Integer> {

  Optional<RoomType> findByName(String name);
}
