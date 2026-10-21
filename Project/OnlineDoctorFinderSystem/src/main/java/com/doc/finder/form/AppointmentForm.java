package com.doc.finder.form;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.doc.finder.dto.AppointmentDTO;
import com.doc.finder.dto.BaseDTO;
import com.doc.finder.dto.DoctorDTO;
import com.doc.finder.dto.UserDTO;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class AppointmentForm extends BaseForm {

	
	private Long appointmentId;
	
	private String dateOfAppointment;
	
	private String slot;
	
	private Long userId;
	
	
	private UserDTO user;
	
	
	private Long doctorId;
	
	
	private String status;
	
	private DoctorDTO doctor;
	@Override
	public BaseDTO getDTO() {
		AppointmentDTO bean = new AppointmentDTO();
		bean.setId(id);
		bean.setAppointmentId(appointmentId);
		bean.setSlot(slot);
		bean.setDateOfAppointment(dateOfAppointment);
		bean.setStatus(status);
		bean.setUser(user);
		bean.setUserId(userId);
		bean.setDoctor(doctor);
		bean.setDoctorId(doctorId);
		bean.setCreatedBy(createdBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedBy(modifiedBy);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		AppointmentDTO bean = (AppointmentDTO)bDto;
		id = bean.getId();
		appointmentId = bean.getAppointmentId();
		user = bean.getUser();
		doctor = bean.getDoctor();
		userId = bean.getUserId();
		doctorId = bean.getDoctorId();
		slot = bean.getSlot();
		dateOfAppointment = bean.getDateOfAppointment();
		status = bean.getStatus();
		createdBy = bean.getCreatedBy();
		modifiedBy = bean.getModifiedBy();
		createdDateTime = bean.getCreatedDatetime();
		modifiedDateTime = bean.getModifiedDatetime();
		
	}

}
