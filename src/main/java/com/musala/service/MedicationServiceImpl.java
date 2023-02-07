package com.musala.service;

import com.musala.model.Medication;
import com.musala.model.MedicationDeliveryState;
import com.musala.repository.MedicationRepository;
import com.musala.repository.base.JpaCrudRepository;
import com.musala.service.base.AbstractJpaCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicationServiceImpl extends AbstractJpaCrudService<Medication> implements MedicationService {

	private final MedicationRepository repository;

	@Override
	public List<Medication> findAllByDroneIdAndState(String droneId, MedicationDeliveryState state) {

		return repository.findAllByDroneIdAndState(droneId, state);
	}

	@Override
	public JpaCrudRepository<Medication> getRepository() {

		return repository;
	}
}
