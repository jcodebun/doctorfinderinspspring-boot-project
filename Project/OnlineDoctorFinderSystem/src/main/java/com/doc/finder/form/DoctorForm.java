package com.doc.finder.form;

import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.doc.finder.dto.BaseDTO;
import com.doc.finder.dto.DoctorDTO;
import com.doc.finder.dto.SpecialityDTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DoctorForm extends BaseForm {
	
	@NotEmpty(message = "Dr. Name is required")
	private String drName;
	@NotEmpty(message = "Dr. Experience is required")
	@Pattern(regexp="(^[0-9]+)*$",message = "Experience is Invalid")
	private String drExperience;
	@Min(message = "Speciality is required", value = 0)
	private Long specialityId;
	@NotEmpty(message = "Location is required")
	private String location;
	@NotNull(message = "Fees is required")
	//@Pattern(regexp="(^[7-9][0-9])*$",message = "Fees is Invalid")
	@Digits(fraction = 3, integer = 10, message = "Fees should be Valid")
	private BigDecimal appointmentFees;
	private SpecialityDTO speciality;

	@Override
	public BaseDTO getDTO() {
		DoctorDTO bean = new DoctorDTO();
		bean.setId(id);
		bean.setAppointmentFees(appointmentFees);
		bean.setDrName(drName);
		bean.setDrExperience(drExperience);
		bean.setSpecialityId(specialityId);
		bean.setSpeciality(speciality);
		bean.setLocation(location);
		bean.setCreatedBy(createdBy);
		bean.setModifiedBy(modifiedBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		DoctorDTO bean = (DoctorDTO)bDto;
		id = bean.getId();
		drName = bean.getDrName();
		drExperience = bean.getDrExperience();
		location = bean.getLocation();
		specialityId = bean.getSpecialityId();
		speciality = bean.getSpeciality();
		appointmentFees = bean.getAppointmentFees();
		createdBy = bean.getCreatedBy();
		createdDateTime = bean.getCreatedDatetime();
		modifiedBy = bean.getModifiedBy();
		modifiedDateTime = bean.getModifiedDatetime();
		
	}

}
