package com.doc.finder.dao;

import java.util.List;

import com.doc.finder.dto.DoctorDTO;

public interface DoctorDAOInt {

	public long add(DoctorDTO dto);

	public void delete(DoctorDTO dto);

	public DoctorDTO findBypk(long pk);

	public void update(DoctorDTO dto);

	public List<DoctorDTO> list();

	public List<DoctorDTO> list(int pageNo, int pageSize);

	public List<DoctorDTO> search(DoctorDTO dto);

	public List<DoctorDTO> search(DoctorDTO dto, int pageNo, int pageSize);

	public List<DoctorDTO> searchDoctorByPatient(DoctorDTO dto);

	public List<DoctorDTO> searchDoctorByPatient(DoctorDTO dto, int pageNo, int pageSize);

	List<DoctorDTO> listBySpecialityId(Long specialityId);
}
