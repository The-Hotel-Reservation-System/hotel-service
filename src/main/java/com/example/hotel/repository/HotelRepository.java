package com.example.hotel.repository;

import com.example.hotel.model.entity.Hotel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface HotelRepository extends CrudRepository<Hotel, BigInteger> {

}
