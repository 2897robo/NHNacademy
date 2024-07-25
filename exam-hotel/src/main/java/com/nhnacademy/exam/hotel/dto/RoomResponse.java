package com.nhnacademy.exam.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nhnacademy.exam.hotel.formatter.TimeFormatterImpl;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomResponse {
	private Long id;
	private String name;
	private int capacity;
	private int floor;
	private boolean hasBathtub;
	private String viewType;
	private LocalDateTime createdAt;

	@JsonProperty("createdAt")
	public String getCreatedAtString() {
		return TimeFormatterImpl.convert(this.createdAt);
	}
}
