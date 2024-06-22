package in.co.rays.project_3.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.FollowUpDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class FollowUpModelHibImpl implements FollowUpModelInt {
	public long add(FollowUpDTO dto) throws ApplicationException, DuplicateRecordException {

		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		FollowUpDTO dtoExist = findByLogin(dto.getDoctor());

		// Check if updated Roll no already exist

		if (dtoExist != null && dtoExist.getId() != dto.getId()) {
			throw new DuplicateRecordException("clint is already exist");
		}

		session = HibDataSource.getSession();
		tx = null;
		try {

			tx = session.beginTransaction();

			session.save(dto);
			dto.getId();
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			// TODO: handle exception
			if (tx != null) {
				tx.rollback();

			}
			throw new ApplicationException("Exception in User Add " + e.getMessage());
		} finally {
			session.close();
		}
		/* log.debug("Model add End"); */
		return dto.getId();

	}

	public void delete(FollowUpDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.delete(dto);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in User Delete" + e.getMessage());
		} finally {
			session.close();
		}
	}

	public void update(FollowUpDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		FollowUpDTO dtoExist = findByLogin(dto.getDoctor());

		// Check if updated Roll no already exist

		if (dtoExist != null && dtoExist.getId() != dto.getId()) {
			throw new DuplicateRecordException("OrderId is already exist");
		}

		// get Student Name
		/*
		 * StudentModelInt sModel = ModelFactory.getInstance().getStudentModel();
		 * StudentDTO studentDTO = sModel.findByPK(dto.getStudentId());
		 * dto.setName(studentDTO.getFirstName() + " " + studentDTO.getLastName());
		 */

		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.update(dto);
			// session.merge(dto);
			// session.saveOrUpdate(dto);
			tx.commit();

		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
				throw new ApplicationException("Exception in Marksheet Update" + e.getMessage());
			}
		} finally {
			session.close();
		}
	}

	public FollowUpDTO findByPK(long pk) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		FollowUpDTO dto = null;
		try {
			session = HibDataSource.getSession();
			dto = (FollowUpDTO) session.get(FollowUpDTO.class, pk);

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in getting User by pk");
		} finally {
			session.close();
		}

		return dto;
	}

	public FollowUpDTO findByLogin(String name) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		FollowUpDTO dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(FollowUpDTO.class);
			criteria.add(Restrictions.eq("name", name));
			List list = criteria.list();
			if (list.size() > 0) {
				dto = (FollowUpDTO) list.get(0);

			}
		} catch (Exception e) {

			throw new ApplicationException("Exception in getting Marksheet by pk" + e.getMessage());

		} finally {
			session.close();
		}
		return dto;
	}

	public List search(FollowUpDTO dto, int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub

		Session session = null;
		ArrayList<FollowUpDTO> list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(FollowUpDTO.class);
			if (dto != null) {
				/*
				 * if (dto.getId() > 0) { criteria.add(Restrictions.eq("id", dto.getId()));
				 * 
				 */

				if (dto.getDoctor() != null && dto.getDoctor().length() > 0) {
					criteria.add(Restrictions.like("doctor", dto.getDoctor() + "%"));
				}
				if (dto.getPatient() != null && dto.getPatient().length() > 0) {
					criteria.add(Restrictions.like("patient", dto.getPatient() + "%"));
				}
				if (dto.getDov() != null && dto.getDov().getDate() > 0) {
					criteria.add(Restrictions.eq("dov", dto.getDov()));
				}
				if (dto.getFees() != null && dto.getFees().length() > 0) {
					criteria.add(Restrictions.like("fees", dto.getFees() + "%"));
				}
			}
			// if pageSize is greater than 0
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = (ArrayList<FollowUpDTO>) criteria.list();
		} catch (HibernateException e) {
			throw new ApplicationException("Exception in user search");
		} finally {
			session.close();
		}

		return list;
	}

	public List list(int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(FollowUpDTO.class);
			if (pageSize > 0) {

				pageNo = ((pageNo - 1) * pageSize) + 1;

				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = criteria.list();

		} catch (Exception e) {

			throw new ApplicationException("Exception in  Marksheet List" + e.getMessage());
		} finally {
			session.close();
		}
		return list;
	}

}
