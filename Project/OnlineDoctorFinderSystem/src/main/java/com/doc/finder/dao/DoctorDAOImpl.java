package com.doc.finder.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.doc.finder.dto.DoctorDTO;

@Repository
public class DoctorDAOImpl implements DoctorDAOInt {

	private static Logger log = Logger.getLogger(DoctorDAOImpl.class.getName());

	@Autowired
	private EntityManager entityManager;

	@Override
	public long add(DoctorDTO dto) {
		// TODO Auto-generated method stub
		log.info("DoctorDAOImpl start method started");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long) session.save(dto);
		log.info("DoctorDAOImpl Add method ends");
		return pk;
	}

	@Override
	public List<DoctorDTO> list() {
		// TODO Auto-generated method stub
		return list(0, 0);
	}

	@Override
	public List<DoctorDTO> list(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("DoctorDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<DoctorDTO> query = session.createQuery("from DoctorDTO", DoctorDTO.class);
		List<DoctorDTO> list = query.getResultList();
		log.info("DoctorDAOImpl List method End");
		return list;
	}

	@Override
	public List<DoctorDTO> search(DoctorDTO dto) {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	@Override
	public List<DoctorDTO> search(DoctorDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("DoctorDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(DoctorDTO.class);
		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getDrExperience() != null && dto.getDrExperience().length() > 0) {
				criteria.add(Restrictions.like("drExperience", dto.getDrExperience() + "%"));
			}
			if (dto.getLocation() != null && dto.getLocation().length() > 0) {
				criteria.add(Restrictions.like("location", dto.getLocation() + "%"));
			}
			if (dto.getDrName() != null && dto.getDrName().length() > 0) {
				criteria.add(Restrictions.like("drName", dto.getDrName() + "%"));
			}

			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult((int) pageNo);
				criteria.setMaxResults(pageSize);
			}
			System.out.println(criteria.list().size());
		}
		log.info("DoctorDAOImpl Search method End");
		return criteria.list();
	}

	@Override
	public List<DoctorDTO> searchDoctorByPatient(DoctorDTO dto) {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	@Override
	public List<DoctorDTO> searchDoctorByPatient(DoctorDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("DoctorDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(DoctorDTO.class);
		if (dto != null) {
			Criterion c1 = Restrictions.eq("drName", dto.getDrName());
			Criterion c2 = Restrictions.eq("drExperience", dto.getDrExperience());
			Criterion c3 = Restrictions.eq("location", dto.getLocation());
			Criterion c4 = Restrictions.eq("specialityId", dto.getSpecialityId());
			Criterion c5 = Restrictions.or(c1, c2, c3, c4);
			criteria.add(c5);

			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult((int) pageNo);
				criteria.setMaxResults(pageSize);
			}
			System.out.println(criteria.list().size());
		}
		log.info("DoctorDAOImpl Search method End");
		return criteria.list();
	}

	@Override
	@Transactional
	public void delete(DoctorDTO dto) {
		// TODO Auto-generated method stub
		entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));

	}

	@Override
	public DoctorDTO findBypk(long pk) {
		// TODO Auto-generated method stub
		log.info("DoctorDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		DoctorDTO dto = (DoctorDTO) session.get(DoctorDTO.class, pk);
		log.info("DoctorDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public void update(DoctorDTO dto) {
		// TODO Auto-generated method stub
		log.info("DoctorDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(dto);
		log.info("DoctorDAOImpl update method End");
	}

	@Override
	public List<DoctorDTO> listBySpecialityId(Long specialityId) {
		// TODO Auto-generated method stub
		log.info("DoctorDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<DoctorDTO> query = session.createQuery("from DoctorDTO where centerId ='" + specialityId + "'",
				DoctorDTO.class);
		List<DoctorDTO> list = query.getResultList();
		log.info("DoctorDAOImpl List method End");
		return list;
	}
}
