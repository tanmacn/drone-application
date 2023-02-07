package com.musala.dto;

import lombok.Data;

@Data
public class DroneBatteryLevelDto {

	private int batteryCapacity;

	public DroneBatteryLevelDto(int batteryCapacity) {

		this.batteryCapacity = batteryCapacity;
	}
}
