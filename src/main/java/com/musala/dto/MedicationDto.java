package com.musala.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.musala.dto.base.DtoBase;
import com.musala.model.MedicationDeliveryState;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MedicationDto extends DtoBase {

	@NotBlank(message = "name is mandatory")
	@Pattern(regexp = "^[0-9a-zA-Z_-]+", message = "name should match regex [^0-9a-zA-Z_-]+")
	private String                  name;
	@Range(min = 1, max = 500, message = "weight should 0 - 500")
	private int                     weight;
	@NotBlank(message = "code is mandatory")
	@Pattern(regexp = "^[0-9A-Z_]+", message = "code should match regex [^0-9A-Z_]+")
	private String                  code;
	private String                  image;
	private MedicationDeliveryState state;
}
