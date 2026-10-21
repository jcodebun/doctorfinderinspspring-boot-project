package com.doc.finder.service;

import java.util.List;

import com.doc.finder.dto.SpecialityDTO;
import com.doc.finder.exception.DuplicateRecordException;

public interface SpecialityServiceInt {

	public long add(SpecialityDTO dto) throws DuplicateRecordException;
	
	public void delete(SpecialityDTO dto);
	
	public SpecialityDTO findBypk(long pk);

	public void update(SpecialityDTO dto) throws DuplicateRecordException;

	public List<SpecialityDTO> list();

	public List<SpecialityDTO> list(int pageNo, int pageSize);

	public List<SpecialityDTO> search(SpecialityDTO dto);

	public List<SpecialityDTO> search(SpecialityDTO dto, int pageNo, int pageSize);
}
