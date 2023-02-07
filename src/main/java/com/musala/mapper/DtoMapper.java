package com.musala.mapper;

import com.musala.dto.DroneDto;
import com.musala.dto.MedicationDto;
import com.musala.model.Drone;
import com.musala.model.Medication;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

@Mapper(
		componentModel = "spring",
		nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public abstract class DtoMapper {

	public abstract Drone map(DroneDto item);

	public abstract DroneDto map(Drone item);


	public abstract Medication map(MedicationDto item);

	public abstract MedicationDto map(Medication item);

}
