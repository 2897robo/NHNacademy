package com.nhnacademy.exam.hotel.service;

import com.nhnacademy.exam.hotel.domain.Hotel;
import com.nhnacademy.exam.hotel.domain.Room;
import com.nhnacademy.exam.hotel.domain.ViewType;
import com.nhnacademy.exam.hotel.dto.RoomRequest;
import com.nhnacademy.exam.hotel.dto.RoomResponse;
import com.nhnacademy.exam.hotel.exception.BadRequestException;
import com.nhnacademy.exam.hotel.formatter.TimeFormatter;
import com.nhnacademy.exam.hotel.formatter.TimeFormatterImpl;
import com.nhnacademy.exam.hotel.repository.HotelRepository;
import com.nhnacademy.exam.hotel.repository.RoomRepository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

// RoomResponse 클래스는 Room Entity 객체를 클라이언트에게 응답하기 위한 DTO 입니다.
// 객실 정보 조회 API 명세서의 Response 양식을 보시고 적절한 형태로 RoomResponse 클래스를 만들어주세요.
// JSON message 의 viewType 속성은 미리 제공한 ViewType enum의 parameter 값을 사용해야 합니다.
// Hint. javax.persistence.AttributeConverter 인터페이스와 @Convert 애너테이션을 사용하면 됩니다.

@Service
@Transactional
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    public List<RoomResponse> getRoomsByHotelId(Long hotelId) {
        if (hotelId == null || hotelId <= 0) {
            throw new BadRequestException("Invalid hotel ID");
        }

        List<Room> rooms = roomRepository.findByHotelId(hotelId);
        return rooms.stream()
            .map(room -> new RoomResponse(
                room.getId(),
                room.getName(),
                room.getCapacity(),
                room.getFloor(),
                room.isHasBathtub(),
                room.getViewType().getParameter(),
                room.getCreatedAt()))
            .collect(Collectors.toList());
    }

    public RoomResponse createRoom(Long hotelId, RoomRequest roomRequest) {
        Hotel hotel = hotelRepository.findById(hotelId)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel not found with id: " + hotelId));

        Room room = new Room(hotel, roomRequest.getName(), roomRequest.getCapacity(), roomRequest.getFloor(), roomRequest.getHasBathtub(), ViewType.fromParameter(roomRequest.getViewType()));

        try {
            Room savedRoom = roomRepository.save(room);
            return new RoomResponse(
                savedRoom.getId(),
                savedRoom.getName(),
                savedRoom.getCapacity(),
                savedRoom.getFloor(),
                savedRoom.isHasBathtub(),
                savedRoom.getViewType().getParameter(),
                savedRoom.getCreatedAt());
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error saving room: " + e.getMessage(), e);
        }
    }
}