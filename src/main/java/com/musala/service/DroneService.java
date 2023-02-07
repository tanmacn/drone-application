package com.musala.service;

import com.musala.model.Drone;
import com.musala.model.DroneState;
import com.musala.service.base.JpaCrudService;

import java.util.List;

public interface DroneService extends JpaCrudService<Drone> {

	List<Drone> findByState(DroneState state);

}
