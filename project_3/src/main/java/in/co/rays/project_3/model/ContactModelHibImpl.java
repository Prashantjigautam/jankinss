package in.co.rays.project_3.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.ContactDTO;
import in.co.rays.project_3.dto.StudentDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class ContactModelHibImpl implements ContactModelInt {
	public long add(ContactDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		Session session = HibDataSource.getSession();
		Transaction tx = null;

		// get Student Name

		ContactDTO duplicateContact = findByLogin(dto.getName());

		if (duplicateContact != null) {
			throw new DuplicateRecordException("Roll Number already exists");
		}

		long pk = 0;
		try {
			tx = session.beginTransaction();
			session.save(dto);
			pk = dto.getId();
			tx.commit();

		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in Contact Add " + e.getMessage());
		} finally {
			session.close();
		}
		return pk;
	}

	/**
	 * delete record from database
	 */
	public void delete(ContactDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		ContactDTO dtoExist = findByPK(dto.getId());
		if (dtoExist == null) {
			throw new ApplicationException("Contact does not exist");
		}

		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.delete(dto);
			tx.commit();

		} catch (HibernateException e) {

			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in Contact Delete" + e.getMessage());
		} finally {
			session.close();
		}
	}

	/**
	 * update record in database
	 */
	public void update(ContactDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		ContactDTO dtoExist = findByLogin(dto.getName());

		// Check if updated Roll no already exist

		if (dtoExist != null && dtoExist.getId() != dto.getId()) {
			throw new DuplicateRecordException(" contact is already exist");
		}

		// get Student Name
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
				throw new ApplicationException("Exception in Contact Update" + e.getMessage());
			}
		} finally {
			session.close();
		}
	}

	/**
	 * get record from database
	 */
	public List list(int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(ContactDTO.class);
			if (pageSize > 0) {

				pageNo = ((pageNo - 1) * pageSize) + 1;

				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = criteria.list();

		} catch (Exception e) {

			throw new ApplicationException("Exception in  Contact List" + e.getMessage());
		} finally {
			session.close();
		}
		return list;
	}

	/**
	 * search record from database
	 */
	public List search(ContactDTO dto, int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(ContactDTO.class);
			/*
			 * if (dto.getId() > 0) { criteria.add(Restrictions.eq("id", dto.getId()));
			 * 
			 */

			if (dto.getName() != null && dto.getName().length() > 0) {
				criteria.add(Restrictions.like("name", dto.getMobile() + "%"));
			}
			if (dto.getCity() != null && dto.getCity().length() > 0) {
				criteria.add(Restrictions.like("city", dto.getName() + "%"));
			}
			if (dto.getDob() != null && dto.getDob().getDate() > 0) {
				criteria.add(Restrictions.eq("dob", dto.getDob()));
			}
			if (dto.getMobile() != null && dto.getMobile().length() > 0) {
				criteria.add(Restrictions.like("mobile", dto.getMobile() + "%"));
			}

			if (pageSize > 0) {
				criteria.setFirstResult((pageNo - 1) * pageSize);
				criteria.setMaxResults(pageSize);
			}
			list = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in Contact Search " + e.getMessage());
		} finally {
			session.close();
		}
		return list;
	}

	/**
	 * find record from database with non business primary key
	 */
	public ContactDTO findByPK(long pk) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		ContactDTO dto = null;
		try {
			session = HibDataSource.getSession();
			dto = (ContactDTO) session.get(ContactDTO.class, pk);

		} catch (Exception e) {

			throw new ApplicationException("Exception in getting Contact by pk" + e.getMessage());

		} finally {
			session.close();
		}
		return dto;
	}

	/**
	 * find record from database with roll number
	 */

	@Override
	public ContactDTO findByLogin(String name) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		ContactDTO dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(ContactDTO.class);
			criteria.add(Restrictions.eq("name", name));
			List list = criteria.list();
			if (list.size() > 0) {
				dto = (ContactDTO) list.get(0);

			}
		} catch (Exception e) {

			throw new ApplicationException("Exception in getting Contact by pk" + e.getMessage());

		} finally {
			session.close();
		}
		return dto;
	}
}

/**
 * get merit list from database
 */
