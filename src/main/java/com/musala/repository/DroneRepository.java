package com.musala.repository;

import com.musala.model.Drone;
import com.musala.model.DroneState;
import com.musala.repository.base.JpaCrudRepository;

import java.util.List;

public interface DroneRepository extends JpaCrudRepository<Drone> {

	List<Drone> findByState(DroneState state);
}
