package in.co.rays.project_3.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.ClintDto;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class ClintModelHibImpl implements ClintModelInt {

	public long add(ClintDto dto) throws ApplicationException, DuplicateRecordException {

		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		ClintDto dtoExist = findByLogin(dto.getName());

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

	public void delete(ClintDto dto) throws ApplicationException {
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

	public void update(ClintDto dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		ClintDto dtoExist = findByLogin(dto.getName());

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

	public ClintDto findByPK(long pk) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		ClintDto dto = null;
		try {
			session = HibDataSource.getSession();
			dto = (ClintDto) session.get(ClintDto.class, pk);

		} catch (HibernateException e) {
			throw new ApplicationException("Exception : Exception in getting User by pk");
		} finally {
			session.close();
		}

		return dto;
	}

	public ClintDto findByLogin(String name) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		ClintDto dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(ClintDto.class);
			criteria.add(Restrictions.eq("name", name));
			List list = criteria.list();
			if (list.size() > 0) {
				dto = (ClintDto) list.get(0);

			}
		} catch (Exception e) {

			throw new ApplicationException("Exception in getting Marksheet by pk" + e.getMessage());

		} finally {
			session.close();
		}
		return dto;
	}

	public List search(ClintDto dto, int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(ClintDto.class);
			if (dto != null) {
				/*if (dto.getId() > 0) {
					criteria.add(Restrictions.eq("id", dto.getId()));

				}*/
				if (dto.getName() != null && dto.getName().length() > 0) {
					criteria.add(Restrictions.like("name", dto.getName() + "%"));
				}
				if (dto.getAddress() != null && dto.getAddress().length() > 0) {
					criteria.add(Restrictions.like("address", dto.getAddress() + "%"));
				}
				if (dto.getDob() != null && dto.getDob().getDate() > 0) {
					criteria.add(Restrictions.eq("dob", dto.getDob()));
				}
				if (dto.getSu() != null && dto.getSu().length() > 0) {
					criteria.add(Restrictions.like("su", dto.getSu() + "%"));
				}

			}
			if (pageSize > 0) {
				criteria.setFirstResult((pageNo - 1) * pageSize);
				criteria.setMaxResults(pageSize);
			}
			list = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in Marksheet Search " + e.getMessage());
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
			Criteria criteria = session.createCriteria(ClintDto.class);
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
