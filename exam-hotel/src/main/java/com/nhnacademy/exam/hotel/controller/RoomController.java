package com.nhnacademy.exam.hotel.controller;

import com.nhnacademy.exam.hotel.dto.RoomRequest;
import com.nhnacademy.exam.hotel.dto.RoomResponse;
import com.nhnacademy.exam.hotel.service.RoomService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/hotel-api/hotels")
public class RoomController {
	private final RoomService roomService;

	public RoomController(RoomService roomService) {
		this.roomService = roomService;
	}

	@GetMapping("/{hotelId}/rooms")
	public List<RoomResponse> getRooms(@PathVariable("hotelId") Long hotelId) {
		return roomService.getRoomsByHotelId(hotelId);
	}

	@PostMapping("/{hotelId}/rooms")
	public ResponseEntity<Map<String, String>> createRoom(
		@PathVariable("hotelId") Long hotelId,
		@RequestBody RoomRequest roomRequest) {

		// 요청 데이터 유효성 검사
		if (!isValidRequest(roomRequest)) {
			return ResponseEntity.badRequest().build();
		}

		// 객실 생성
		RoomResponse createdRoom = roomService.createRoom(hotelId, roomRequest);

		// 응답 생성
		Map<String, String> response = Map.of("id", createdRoom.getId().toString());
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	private boolean isValidRequest(RoomRequest roomRequest) {
		return roomRequest.getName() != null &&
			roomRequest.getCapacity() != null &&
			roomRequest.getFloor() != null &&
			roomRequest.getHasBathtub() != null &&
			roomRequest.getViewType() != null &&
			(roomRequest.getViewType().equals("cityView") ||
				roomRequest.getViewType().equals("oceanView") ||
				roomRequest.getViewType().equals("mountainView"));
	}
}
