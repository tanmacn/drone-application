package com.musala.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DroneLoadingResponseDto {

	private String message;

	public DroneLoadingResponseDto(String message) {

		this.message = message;
	}
}
