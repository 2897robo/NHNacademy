package com.nhnacademy.exam.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomRequest {
	private String name;
	private Integer capacity;
	private Integer floor;
	private Boolean hasBathtub;
	private String viewType;
}
