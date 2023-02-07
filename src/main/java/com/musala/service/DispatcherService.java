package com.musala.service;


import com.musala.dto.DroneBatteryLevelDto;
import com.musala.dto.DroneLoadingResponseDto;
import com.musala.dto.MedicationDto;
import com.musala.model.Drone;
import com.musala.model.Medication;
import com.musala.model.MedicationDeliveryState;

import java.util.List;

public interface DispatcherService {

	DroneLoadingResponseDto loadDrone(String droneId, MedicationDto load);

	DroneBatteryLevelDto checkDroneBatteryLevel(String droneId);

	List<Medication> checkDroneItems(String droneId, MedicationDeliveryState state);

	List<Drone> checkAvailableDrones();

}
