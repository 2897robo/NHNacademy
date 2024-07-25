package com.nhnacademy.exam.hotel.converter;

import com.nhnacademy.exam.hotel.domain.ViewType;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ViewTypeConverter implements AttributeConverter<ViewType, Integer> {
	@Override
	public Integer convertToDatabaseColumn(ViewType viewType) {
		if (viewType == null) {
			return null;
		}
		return viewType.getDbValue();
	}

	@Override
	public ViewType convertToEntityAttribute(Integer dbValue) {
		if (dbValue == null) {
			return null;
		}
		return ViewType.fromDbValue(dbValue);
	}
}
