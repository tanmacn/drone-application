package com.musala.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.musala.dto.base.DtoBase;
import com.musala.model.DroneModel;
import com.musala.model.DroneState;
import com.musala.model.Medication;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DroneDto extends DtoBase {

	@NotBlank(message = "serialNumber is mandatory")
	@Size(min = 6, max = 100, message = "serialNumber should be between 6 and 100 characters")
	private String           serialNumber;
	@NotNull(message = "model is mandatory")
	private DroneModel       model;
	@Range(min = 1, max = 500, message = "maximum weight should be 1 - 500")
	private int              weight;
	@Range(min = 0, max = 100, message = "battery capacity should be 0 - 100")
	private int              batteryCapacity;
	private DroneState       state;
	private List<Medication> medications;

	public DroneDto(String serialNumber, DroneModel model, int weight, int batteryCapacity) {

		this.serialNumber = serialNumber;
		this.model = model;
		this.weight = weight;
		this.batteryCapacity = batteryCapacity;
	}
}
