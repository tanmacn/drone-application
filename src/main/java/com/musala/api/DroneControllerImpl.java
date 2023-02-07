package com.musala.api;

import com.musala.api.base.AbstractApi;
import com.musala.dto.DroneDto;
import com.musala.mapper.DtoMapper;
import com.musala.model.Drone;
import com.musala.service.DroneService;
import com.musala.service.base.JpaCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class DroneControllerImpl extends AbstractApi<DroneDto, Drone> implements DroneController {

	private final DtoMapper    mapper;
	private final DroneService service;

	@Override
	public ResponseEntity<DroneDto> post(@Valid @RequestBody DroneDto api) {

		return super.post(api);
	}

	@Override
	protected JpaCrudService<Drone> getService() {

		return service;
	}

	@Override
	protected DroneDto map(Drone var1) {

		return mapper.map(var1);
	}

	@Override
	protected Drone map(DroneDto var1) {

		return mapper.map(var1);
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
