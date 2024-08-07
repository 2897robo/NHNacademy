package com.nhnacademy.exam.hotel.repository;

import com.nhnacademy.exam.hotel.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
	List<Room> findByHotelId(Long hotelId);
}
