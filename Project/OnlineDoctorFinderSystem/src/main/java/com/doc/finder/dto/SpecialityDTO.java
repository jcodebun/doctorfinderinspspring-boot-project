package com.doc.finder.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;


import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "d_speciality")
@Setter
@Getter
public class SpecialityDTO extends BaseDTO {

	@Column(name = "speciality", length = 255)
	private String speciality;
	
	@OneToMany(
			mappedBy = "speciality",
			cascade = CascadeType.ALL,
			orphanRemoval = true
			)
	@Cascade({org.hibernate.annotations.CascadeType.DELETE})
	private List<DoctorDTO> listOfDoctors  = new ArrayList<>();
	
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
