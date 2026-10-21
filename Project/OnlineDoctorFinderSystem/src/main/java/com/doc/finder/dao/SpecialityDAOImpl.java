package com.doc.finder.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.doc.finder.dto.SpecialityDTO;
import com.doc.finder.dto.UserDTO;

@Repository
public class SpecialityDAOImpl implements SpecialityDAOInt {

	private static Logger log = Logger.getLogger(SpecialityDAOImpl.class.getName());

	@Autowired
	private EntityManager entityManager;

	@Override
	public long add(SpecialityDTO dto) {
		// TODO Auto-generated method stub
		log.info("SpecialityDAOImpl start method started");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long) session.save(dto);
		log.info("SpecialityDAOImpl Add method ends");
		return pk;
	}

	@Override
	public List<SpecialityDTO> list() {
		// TODO Auto-generated method stub
		return list(0, 0);
	}

	@Override
	public List<SpecialityDTO> list(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("SpecialityDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<SpecialityDTO> query = session.createQuery("from SpecialityDTO", SpecialityDTO.class);
		List<SpecialityDTO> list = query.getResultList();
		log.info("SpecialityDAOImpl List method End");
		return list;
	}

	@Override
	public List<SpecialityDTO> search(SpecialityDTO dto) {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	@Override
	public List<SpecialityDTO> search(SpecialityDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("SpecialityDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(SpecialityDTO.class);
		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getSpeciality() != null && dto.getSpeciality().length() > 0) {
				criteria.add(Restrictions.like("speciality", dto.getSpeciality() + "%"));
			}

			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult((int) pageNo);
				criteria.setMaxResults(pageSize);
			}
		}
		log.info("SpecialityDAOImpl Search method End");
		System.out.println("lis " + criteria.list().size());
		return criteria.list();
	}

	@Override
	@Transactional
	public void delete(SpecialityDTO dto) {
		// TODO Auto-generated method stub
		entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));

	}

	@Override
	public SpecialityDTO findBypk(long pk) {
		// TODO Auto-generated method stub
		log.info("SpecialityDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		SpecialityDTO dto = (SpecialityDTO) session.get(SpecialityDTO.class, pk);
		log.info("SpecialityDAOImpl FindByPk method End");
		return dto;
	}
	
	@Override
	public SpecialityDTO findBySpeciality(String sp) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(SpecialityDTO.class);
		criteria.add(Restrictions.eq("speciality", sp));
		SpecialityDTO dto = (SpecialityDTO) criteria.uniqueResult();
		return dto;
	}

	@Override
	public void update(SpecialityDTO dto) {
		// TODO Auto-generated method stub
		log.info("SpecialityDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(dto);
		log.info("SpecialityDAOImpl update method End");
	}
}
