package com.musala.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.musala.model.base.JpaModelBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Where(clause = "deleted is null")
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
public class Drone extends JpaModelBase {

	private String           serialNumber;
	@Enumerated(EnumType.STRING)
	private DroneModel       model;
	private int              weight;
	private int              batteryCapacity;
	@Enumerated(EnumType.STRING)
	private DroneState       state;
	@JsonManagedReference
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "drone")
	@Where(clause = "deleted is null")
	private List<Medication> medications;
}
