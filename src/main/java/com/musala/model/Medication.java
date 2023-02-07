package com.musala.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.musala.model.base.JpaModelBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Where(clause = "deleted is null")
@EqualsAndHashCode(callSuper = false)
@Getter
@Setter
public class Medication extends JpaModelBase {

	private String                  name;
	private int                     weight;
	private String                  code;
	private String                  image;
	@Enumerated(EnumType.STRING)
	private MedicationDeliveryState state;
	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "drone_id")
	@Where(clause = "deleted is null")
	private Drone                   drone;
}
