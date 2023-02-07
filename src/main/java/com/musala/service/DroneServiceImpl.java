package com.musala.service;

import com.musala.model.Drone;
import com.musala.model.DroneState;
import com.musala.repository.DroneRepository;
import com.musala.repository.base.JpaCrudRepository;
import com.musala.service.base.AbstractJpaCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DroneServiceImpl extends AbstractJpaCrudService<Drone> implements DroneService {

	private final DroneRepository repository;

	@Override
	public List<Drone> findByState(DroneState state) {

		return repository.findByState(state);
	}

	@Override
	public JpaCrudRepository<Drone> getRepository() {

		return repository;
	}

	@Override
	public Drone post(Drone item) {

		item.setState(DroneState.IDLE);
		return super.post(item);
	}
}
