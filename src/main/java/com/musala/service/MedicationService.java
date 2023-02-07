package com.musala.service;


import com.musala.model.Medication;
import com.musala.model.MedicationDeliveryState;
import com.musala.service.base.JpaCrudService;

import java.util.List;

public interface MedicationService extends JpaCrudService<Medication> {

	List<Medication> findAllByDroneIdAndState(String droneId, MedicationDeliveryState state);

}
