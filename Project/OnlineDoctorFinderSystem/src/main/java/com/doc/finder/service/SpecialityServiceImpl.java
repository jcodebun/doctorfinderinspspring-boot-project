package com.doc.finder.service;

import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doc.finder.dao.SpecialityDAOInt;
import com.doc.finder.dto.SpecialityDTO;
import com.doc.finder.exception.DuplicateRecordException;

@Service
public class SpecialityServiceImpl implements SpecialityServiceInt {

	private static Logger log = Logger.getLogger(SpecialityServiceImpl.class.getName());

	@Autowired
	private SpecialityDAOInt dao;

	@Override
	@Transactional
	public long add(SpecialityDTO dto) throws DuplicateRecordException {
		log.info("SpecialityServiceImpl Add method start");
		SpecialityDTO existdto = dao.findBySpeciality(dto.getSpeciality());
		if (existdto != null)
			throw new DuplicateRecordException("Speciality Already Exist");
		long pk = dao.add(dto);
		log.info("SpecialityServiceImpl Add method end");
		return pk;
	}

	@Override
	public List<SpecialityDTO> list() {
		// TODO Auto-generated method stub
		log.info("SpecialityServiceImpl list method start");
		List<SpecialityDTO> list = dao.list();
		log.info("SpecialityServiceImpl list method end");
		return list;
	}

	@Override
	public List<SpecialityDTO> list(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("SpecialityServiceImpl list method start");
		List<SpecialityDTO> list = dao.list(pageNo, pageSize);
		log.info("SpecialityServiceImpl list method end");
		return list;
	}

	@Override
	public List<SpecialityDTO> search(SpecialityDTO dto) {
		// TODO Auto-generated method stub
		log.info("SpecialityServiceImpl search method start");
		List<SpecialityDTO> list = dao.search(dto);
		log.info("SpecialityServiceImpl search method end");
		return list;
	}

	@Override
	public List<SpecialityDTO> search(SpecialityDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("SpecialityServiceImpl search method start");
		List<SpecialityDTO> list = dao.search(dto, pageNo, pageSize);
		log.info("SpecialityServiceImpl search method end");
		return list;
	}

	@Override
	@Transactional
	public void delete(SpecialityDTO dto) {
		// TODO Auto-generated method stub
		log.info("SpecialityServiceImpl delete method start");
		dao.delete(dto);
		log.info("SpecialityServiceImpl delete method end");
	}

	@Override
	public SpecialityDTO findBypk(long pk) {
		// TODO Auto-generated method stub
		log.info("SpecialityServiceImpl findBypk method start");
		SpecialityDTO dto = dao.findBypk(pk);
		log.info("SpecialityServiceImpl findBypk method end");
		return dto;
	}

	@Override
	@Transactional
	public void update(SpecialityDTO dto) throws DuplicateRecordException {
		SpecialityDTO existdto = dao.findBySpeciality(dto.getSpeciality());
		if (existdto != null)
			throw new DuplicateRecordException("Speciality Already Exist");
		dao.update(dto);
	}

}
