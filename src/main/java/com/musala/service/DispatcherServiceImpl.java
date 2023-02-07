package com.musala.service;

import com.musala.dto.DroneBatteryLevelDto;
import com.musala.dto.DroneLoadingResponseDto;
import com.musala.dto.MedicationDto;
import com.musala.exception.ValidationException;
import com.musala.mapper.DtoMapper;
import com.musala.model.Drone;
import com.musala.model.DroneState;
import com.musala.model.Medication;
import com.musala.model.MedicationDeliveryState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DispatcherServiceImpl implements DispatcherService {

	private final DtoMapper         dtoMapper;
	private final DroneService      droneService;
	private final MedicationService medicationService;

	@Override
	public DroneLoadingResponseDto loadDrone(String droneId, MedicationDto load) {

		Drone drone = droneService.get(droneId);

		if (drone == null) {
			throw new ValidationException(String.format("Drone with id: %s cannot be found", droneId));
		}

		Medication medication = dtoMapper.map(load);
		medication.setDrone(drone);
		medication.setState(MedicationDeliveryState.PENDING);

		if (load.getWeight() > drone.getWeight()) {
			throw new ValidationException("Load weight should be less or equal to drone weight");
		}

		if (drone.getBatteryCapacity() < 25) {
			throw new ValidationException("Drone battery capacity is less than 25%");
		}

		if (drone.getState().compareTo(DroneState.IDLE) != 0) {
			throw new ValidationException("Drone should be idle for it to be loaded");
		}

		medicationService.post(medication);
		drone.setState(DroneState.LOADING);
		droneService.put(drone);
		return new DroneLoadingResponseDto("Saved successfully");
	}

	@Override
	public DroneBatteryLevelDto checkDroneBatteryLevel(String droneId) {

		Drone drone = droneService.get(droneId);

		if (drone == null) {
			throw new ValidationException(String.format("Drone with id: %s cannot be found", droneId));
		}
		return new DroneBatteryLevelDto(drone.getBatteryCapacity());
	}

	@Override
	public List<Medication> checkDroneItems(String droneId, MedicationDeliveryState state) {

		return medicationService.findAllByDroneIdAndState(droneId, state);
	}

	@Override
	public List<Drone> checkAvailableDrones() {

		List<Drone> availableDrones = droneService.findByState(DroneState.IDLE);
		if (availableDrones == null || availableDrones.isEmpty()) {
			return null;
		}

		availableDrones.forEach(drone -> drone.setMedications(null));

		return availableDrones;
	}


	// a periodic task to check battery and log
	@Scheduled(initialDelayString = "6000",
			fixedRateString = "4000")
	public void checkBatteryLevelAndLog() {

		List<Drone> droneList = droneService.get().getContent();

		droneList.forEach(drone -> {
			log.info("Drone id: {}, model: {}, battery capacity: {}%", drone.getId(), drone.getModel(),
					drone.getBatteryCapacity());
		});
	}
}
