package com.doc.finder.dao;

import java.util.List;


import com.doc.finder.dto.SpecialityDTO;

public interface SpecialityDAOInt {

	public long add(SpecialityDTO dto);
	
	public void delete(SpecialityDTO dto);
	
	public SpecialityDTO findBypk(long pk);
	
	public void update(SpecialityDTO dto);
	
	public List<SpecialityDTO> list();
	
	public List<SpecialityDTO>list(int pageNo,int pageSize);
	
	public List<SpecialityDTO> search(SpecialityDTO dto);
	
	public List<SpecialityDTO> search(SpecialityDTO dto,int pageNo,int pageSize);

	SpecialityDTO findBySpeciality(String sp);
}
