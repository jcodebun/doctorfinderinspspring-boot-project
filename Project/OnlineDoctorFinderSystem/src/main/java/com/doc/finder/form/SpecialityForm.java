package com.doc.finder.form;

import javax.validation.constraints.NotEmpty;

import com.doc.finder.dto.BaseDTO;
import com.doc.finder.dto.SpecialityDTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SpecialityForm extends BaseForm {

	@NotEmpty(message = "Speciality is required")
	private String speciality;

	@Override
	public BaseDTO getDTO() {
		SpecialityDTO bean = new SpecialityDTO();
		bean.setId(id);
		bean.setSpeciality(speciality);
		bean.setCreatedBy(createdBy);
		bean.setCreatedDatetime(createdDateTime);
		bean.setModifiedBy(modifiedBy);
		bean.setModifiedDatetime(modifiedDateTime);
		return bean;
	}

	@Override
	public void populate(BaseDTO bDto) {
		SpecialityDTO bean = (SpecialityDTO) bDto;
		id = bean.getId();
		speciality = bean.getSpeciality();
		createdBy = bean.getCreatedBy();
		modifiedBy = bean.getModifiedBy();
		createdDateTime = bean.getCreatedDatetime();
		modifiedDateTime = bean.getModifiedDatetime();

	}

}
