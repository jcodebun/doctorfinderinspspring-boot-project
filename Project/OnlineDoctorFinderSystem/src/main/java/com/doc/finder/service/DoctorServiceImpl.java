package com.doc.finder.service;

import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doc.finder.dao.DoctorDAOInt;
import com.doc.finder.dto.DoctorDTO;
import com.doc.finder.exception.DuplicateRecordException;

@Service
public class DoctorServiceImpl implements DoctorServiceInt {

	private static Logger log = Logger.getLogger(DoctorServiceImpl.class.getName());

	@Autowired
	private DoctorDAOInt dao;

	@Override
	@Transactional
	public long add(DoctorDTO dto) throws DuplicateRecordException {
		log.info("DoctorServiceImpl Add method start");
		long pk = dao.add(dto);
		log.info("DoctorServiceImpl Add method end");
		return pk;
	}

	

	@Override
	public List<DoctorDTO> list() {
		// TODO Auto-generated method stub
		log.info("DoctorServiceImpl list method start");
		List<DoctorDTO> list = dao.list();
		log.info("DoctorServiceImpl list method end");
		return list;
	}

	@Override
	public List<DoctorDTO> list(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("DoctorServiceImpl list method start");
		List<DoctorDTO> list = dao.list(pageNo, pageSize);
		log.info("DoctorServiceImpl list method end");
		return list;
	}

	@Override
	public List<DoctorDTO> search(DoctorDTO dto) {
		// TODO Auto-generated method stub
		log.info("DoctorServiceImpl search method start");
		List<DoctorDTO> list = dao.search(dto);
		log.info("DoctorServiceImpl search method end");
		return list;
	}

	@Override
	public List<DoctorDTO> search(DoctorDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("DoctorServiceImpl search method start");
		List<DoctorDTO> list = dao.search(dto, pageNo, pageSize);
		log.info("DoctorServiceImpl search method end");
		return list;
	}
	
	@Override
	public List<DoctorDTO> searchDoctorByPatient(DoctorDTO dto) {
		// TODO Auto-generated method stub
		log.info("DoctorServiceImpl search method start");
		List<DoctorDTO> list = dao.searchDoctorByPatient(dto);
		log.info("DoctorServiceImpl search method end");
		return list;
	}

	@Override
	public List<DoctorDTO> searchDoctorByPatient(DoctorDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("DoctorServiceImpl search method start");
		List<DoctorDTO> list = dao.searchDoctorByPatient(dto, pageNo, pageSize);
		log.info("DoctorServiceImpl search method end");
		return list;
	}



	@Override
	@Transactional
	public void delete(DoctorDTO dto) {
		// TODO Auto-generated method stub
		log.info("DoctorServiceImpl delete method start");
		dao.delete(dto);
		log.info("DoctorServiceImpl delete method end");
	}



	@Override
	public DoctorDTO findBypk(long pk) {
		// TODO Auto-generated method stub
		log.info("DoctorServiceImpl findBypk method start");
		DoctorDTO dto = dao.findBypk(pk);
		log.info("DoctorServiceImpl findBypk method end");
		return dto;
	}



	@Override
	@Transactional
	public void update(DoctorDTO dto) throws DuplicateRecordException {
		// TODO Auto-generated method stub
		dao.update(dto);
	}



	@Override
	public List<DoctorDTO> listBySpecialityId(Long specialityId) {
		// TODO Auto-generated method stub
		log.info("DoctorServiceImpl listBySpecialityId method start");
		List<DoctorDTO> list = dao.listBySpecialityId(specialityId);
		log.info("DoctorServiceImpl listBySpecialityId method end");
		return list;
	}
	
	
}
