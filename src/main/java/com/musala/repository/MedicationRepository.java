package com.musala.repository;

import com.musala.model.Medication;
import com.musala.model.MedicationDeliveryState;
import com.musala.repository.base.JpaCrudRepository;

import java.util.List;

public interface MedicationRepository extends JpaCrudRepository<Medication> {

	List<Medication> findAllByDroneIdAndState(String droneId, MedicationDeliveryState state);
}
