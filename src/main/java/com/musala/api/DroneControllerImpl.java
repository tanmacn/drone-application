package com.musala.api;

import com.musala.api.base.AbstractApi;
import com.musala.dto.DroneDto;
import com.musala.mapper.DtoMapper;
import com.musala.model.Drone;
import com.musala.service.DroneService;
import com.musala.service.base.JpaCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
}
