package com.musala.api;

import com.musala.dto.DroneBatteryLevelDto;
import com.musala.dto.DroneDto;
import com.musala.dto.DroneLoadingResponseDto;
import com.musala.dto.MedicationDto;
import com.musala.mapper.DtoMapper;
import com.musala.model.MedicationDeliveryState;
import com.musala.service.DispatcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("dispatch")
@RestController
@RequiredArgsConstructor
public class DispatchController {

	private final DtoMapper         mapper;
	private final DispatcherService dispatcherService;

	@PostMapping("/load-drone/{drone-id}")
	@ResponseBody
	public DroneLoadingResponseDto loadDrone(@Valid @RequestBody MedicationDto load,
	                                         @PathVariable("drone-id") String droneId) {

		return dispatcherService.loadDrone(droneId, load);
	}

	@GetMapping("/check-drone-items/{droneId}/delivery-state/{state}")
	public List<MedicationDto> checkDroneItems(@PathVariable String droneId,
	                                           @PathVariable MedicationDeliveryState state) {

		return dispatcherService.checkDroneItems(droneId, state).stream().map(mapper::map).toList();
	}

	@GetMapping("/check-available-drones")
	public List<DroneDto> checkAvailableDrones() {

		return dispatcherService.checkAvailableDrones().stream().map(mapper::map).toList();
	}

	@GetMapping("/check-drone-battery-level/{droneId}")
	public DroneBatteryLevelDto checkDroneBatteryLevel(@PathVariable String droneId) {

		return dispatcherService.checkDroneBatteryLevel(droneId);
	}


	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
			MethodArgumentNotValidException ex) {

		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

}
