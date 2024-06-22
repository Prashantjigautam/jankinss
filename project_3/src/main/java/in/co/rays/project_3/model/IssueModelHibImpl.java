package in.co.rays.project_3.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.IssueDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class IssueModelHibImpl implements IssueModelInt {
	public long add(IssueDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		Session session = HibDataSource.getSession();
		Transaction tx = null;

		// get Student title

		IssueDTO duplicateIssue = findByLogin(dto.getTitle());

		if (duplicateIssue != null) {
			throw new DuplicateRecordException("Issue is  already exists");
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
			throw new ApplicationException("Exception in Issue Add " + e.getMessage());
		} finally {
			session.close();
		}
		return pk;
	}

	/**
	 * delete record from database
	 */
	public void delete(IssueDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		IssueDTO dtoExist = findByPK(dto.getId());
		if (dtoExist == null) {
			throw new ApplicationException("Issue does not exist");
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
			throw new ApplicationException("Exception in Issue Delete" + e.getMessage());
		} finally {
			session.close();
		}
	}

	/**
	 * update record in database
	 */
	public void update(IssueDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		IssueDTO dtoExist = findByLogin(dto.getTitle());

		// Check if updated Roll no already exist

		if (dtoExist != null && dtoExist.getId() != dto.getId()) {
			throw new DuplicateRecordException(" Issue is already exist");
		}

		// get Student title
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
				throw new ApplicationException("Exception in Issue Update" + e.getMessage());
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
			Criteria criteria = session.createCriteria(IssueDTO.class);
			if (pageSize > 0) {

				pageNo = ((pageNo - 1) * pageSize) + 1;

				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = criteria.list();

		} catch (Exception e) {

			throw new ApplicationException("Exception in  Issue List" + e.getMessage());
		} finally {
			session.close();
		}
		return list;
	}

	/**
	 * search record from database
	 */
	public List search(IssueDTO dto, int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		List list = null;

		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(IssueDTO.class);
			/*
			 * if (dto.getId() > 0) { criteria.add(Restrictions.eq("id", dto.getId()));
			 * 
			 */
			if (dto != null) {
				if (dto.getTitle() != null && dto.getTitle().length() > 0) {
					criteria.add(Restrictions.like("title", dto.getTitle() + "%"));
				}
				if (dto.getDescription() != null && dto.getDescription().length() > 0) {
					criteria.add(Restrictions.like("description", dto.getDescription() + "%"));
				}

				if (dto.getStatus() != null && dto.getStatus().length() > 0) {
					criteria.add(Restrictions.like("status", dto.getStatus() + "%"));
				}
				if (dto.getDate() != null && dto.getDate().getDate() > 0) {
					criteria.add(Restrictions.eq("date", dto.getDate()));
				}
				if (dto.getAssignTo() != null && dto.getAssignTo().length() > 0) {
					criteria.add(Restrictions.like("assignTo", dto.getAssignTo() + "%"));
				}
			}

			if (pageSize > 0) {
				criteria.setFirstResult((pageNo - 1) * pageSize);
				criteria.setMaxResults(pageSize);
			}
			list = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in Issue Search " + e.getMessage());
		} finally {
			session.close();
		}
		return list;
	}

	/**
	 * find record from database with non business primary key
	 */
	public IssueDTO findByPK(long pk) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		IssueDTO dto = null;
		try {
			session = HibDataSource.getSession();
			dto = (IssueDTO) session.get(IssueDTO.class, pk);

		} catch (Exception e) {

			throw new ApplicationException("Exception in getting Issue by pk" + e.getMessage());

		} finally {
			session.close();
		}
		return dto;
	}

	/**
	 * find record from database with roll number
	 */

	public IssueDTO findByLogin(String title) throws ApplicationException {
		Session session = null;
		IssueDTO dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(IssueDTO.class);
			criteria.add(Restrictions.eq("title", title));
			List list = criteria.list();
			if (list.size() > 0) {
				dto = (IssueDTO) list.get(0);

			}
		} catch (Exception e) {

			throw new ApplicationException("Exception in getting Issue by pk" + e.getMessage());

		} finally {
			session.close();
		}
		return dto;
	}

}
