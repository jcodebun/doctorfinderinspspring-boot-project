package com.doc.finder.service;

import java.util.List;

import com.doc.finder.dto.AppointmentDTO;
import com.doc.finder.exception.DuplicateRecordException;

public interface AppointmentServiceInt {

	public long add(AppointmentDTO dto) throws DuplicateRecordException;
	
	public void delete(AppointmentDTO dto);
	
	public void rejectAppointment(AppointmentDTO dto);
	
	public AppointmentDTO findBypk(long pk);

	public void update(AppointmentDTO dto) throws DuplicateRecordException;

	public List<AppointmentDTO> list();

	public List<AppointmentDTO> list(int pageNo, int pageSize);

	public List<AppointmentDTO> search(AppointmentDTO dto);

	public List<AppointmentDTO> search(AppointmentDTO dto, int pageNo, int pageSize);
}
