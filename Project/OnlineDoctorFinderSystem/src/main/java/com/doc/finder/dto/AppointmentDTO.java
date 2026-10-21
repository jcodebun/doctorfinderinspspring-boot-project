package com.doc.finder.dto;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "d_appointment")
@Setter
@Getter
public class AppointmentDTO extends BaseDTO {

	@Column(name = "appointment_id", length = 255)
	private Long appointmentId;
	@Column(name = "date_of_appointment", length = 255)
	private String dateOfAppointment;
	@Column(name = "slot", length = 255)
	private String slot;
	@Column(name = "user_id", length = 255)
	private Long userId;

	@ManyToOne
	@JoinColumn(name = "user")
	private UserDTO user;

	@Column(name = "doctor_id", length = 255)
	private Long doctorId;

	@Column(name = "status", length = 255)
	private String status;
	@ManyToOne
	@JoinColumn(name = "doctor")
	private DoctorDTO doctor;

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
