package com.nhnacademy.exam.hotel.domain;

import com.nhnacademy.exam.hotel.converter.ViewTypeConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "rooms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_id")
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hotel_id", nullable = false)
	private Hotel hotel;

	@Column(nullable = false, length = 100)
	private String name;

	@Column(nullable = false)
	private int capacity;

	@Column(nullable = false)
	private int floor;

	@Column(name = "bathtub_flag", nullable = false)
	private boolean hasBathtub;

	@Convert(converter = ViewTypeConverter.class)
	@Column(name = "view_type", nullable = false)
	private ViewType viewType;

	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt = LocalDateTime.now();

	@Column(name = "peak_price")
	private BigDecimal peakPrice;

	@Column(name = "non_peak_price")
	private BigDecimal nonPeakPrice;

	@Builder
	public Room(Hotel hotel, String name, int capacity, int floor, boolean hasBathtub, ViewType viewType) {
		this.hotel = hotel;
		this.name = name;
		this.capacity = capacity;
		this.floor = floor;
		this.hasBathtub = hasBathtub;
		this.viewType = viewType;
		this.createdAt = LocalDateTime.now();
	}
}
