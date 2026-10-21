package com.doc.finder.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "d_doctor")
@Setter
@Getter
public class DoctorDTO extends BaseDTO {

	@Column(name = "dr_name", length = 255)
	private String drName;
	@Column(name = "dr_experience", length = 255)
	private String drExperience;
	@ManyToOne
	@JoinColumn(name = "speciality")
	private SpecialityDTO speciality;
	@Column(name = "speciality_id", length = 255)
	private Long specialityId;
	@Column(name = "dr_location", length = 255)
	private String location;
	@Column(name = "dr_fees", length = 255)
	private BigDecimal appointmentFees;
	
	@OneToMany(
			mappedBy = "doctor",
			cascade = CascadeType.ALL,
			orphanRemoval = true
			)
	@Cascade({org.hibernate.annotations.CascadeType.DELETE})
	private List<AppointmentDTO> appointmentList  = new ArrayList<>();
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

}
