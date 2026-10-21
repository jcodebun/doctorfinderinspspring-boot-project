package com.doc.finder.service;

import java.util.List;

import com.doc.finder.dto.DoctorDTO;
import com.doc.finder.exception.DuplicateRecordException;

public interface DoctorServiceInt {

	public long add(DoctorDTO dto) throws DuplicateRecordException;
	
	public void delete(DoctorDTO dto);
	
	public DoctorDTO findBypk(long pk);

	public void update(DoctorDTO dto) throws DuplicateRecordException;

	public List<DoctorDTO> list();

	public List<DoctorDTO> list(int pageNo, int pageSize);

	public List<DoctorDTO> search(DoctorDTO dto);

	public List<DoctorDTO> search(DoctorDTO dto, int pageNo, int pageSize);
	
	public List<DoctorDTO> searchDoctorByPatient(DoctorDTO dto);

	public List<DoctorDTO> searchDoctorByPatient(DoctorDTO dto, int pageNo, int pageSize);
	
	public List<DoctorDTO> listBySpecialityId(Long specialityId);
}
