package in.co.rays.project_3.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.ProjectDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class ProjectModelHibImpl implements ProjectModelInt {
	public long add(ProjectDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		Session session = HibDataSource.getSession();
		Transaction tx = null;

		// get Student title

		ProjectDTO duplicateProject = findByLogin(dto.getCategory());

		if (duplicateProject != null) {
			throw new DuplicateRecordException("Project is  already exists");
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
			throw new ApplicationException("Exception in Project Add " + e.getMessage());
		} finally {
			session.close();
		}
		return pk;
	}

	/**
	 * delete record from database
	 */
	public void delete(ProjectDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		ProjectDTO dtoExist = findByPK(dto.getId());
		if (dtoExist == null) {
			throw new ApplicationException("Project does not exist");
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
			throw new ApplicationException("Exception in Project Delete" + e.getMessage());
		} finally {
			session.close();
		}
	}

	/**
	 * update record in database
	 */
	public void update(ProjectDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		ProjectDTO dtoExist = findByLogin(dto.getCategory());

		// Check if updated Roll no already exist

		if (dtoExist != null && dtoExist.getId() != dto.getId()) {
			throw new DuplicateRecordException(" Project is already exist");
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
				throw new ApplicationException("Exception in Project Update" + e.getMessage());
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
			Criteria criteria = session.createCriteria(ProjectDTO.class);
			if (pageSize > 0) {

				pageNo = ((pageNo - 1) * pageSize) + 1;

				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = criteria.list();

		} catch (Exception e) {

			throw new ApplicationException("Exception in  Project List" + e.getMessage());
		} finally {
			session.close();
		}
		return list;
	}

	/**
	 * search record from database
	 */
	public List search(ProjectDTO dto, int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		List list = null;

		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(ProjectDTO.class);
			/*
			 * if (dto.getId() > 0) { criteria.add(Restrictions.eq("id", dto.getId()));
			 * 
			 */
			if (dto != null) {
				if (dto.getName() != null && dto.getName().length() > 0) {
					criteria.add(Restrictions.like("name", dto.getName() + "%"));
				}
				if (dto.getVersion() > 0) {
					criteria.add(Restrictions.eq("version", dto.getVersion()));
				}

				if (dto.getCategory() != null && dto.getCategory().length() > 0) {
					criteria.add(Restrictions.like("category", dto.getCategory() + "%"));
				}
				if (dto.getDate() != null && dto.getDate().getDate() > 0) {
					criteria.add(Restrictions.eq("date", dto.getDate()));
				}

			}

			if (pageSize > 0) {
				criteria.setFirstResult((pageNo - 1) * pageSize);
				criteria.setMaxResults(pageSize);
			}
			list = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in Project Search " + e.getMessage());
		} finally {
			session.close();
		}
		return list;
	}

	/**
	 * find record from database with non business primary key
	 */
	public ProjectDTO findByPK(long pk) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		ProjectDTO dto = null;
		try {
			session = HibDataSource.getSession();
			dto = (ProjectDTO) session.get(ProjectDTO.class, pk);

		} catch (Exception e) {

			throw new ApplicationException("Exception in getting Project by pk" + e.getMessage());

		} finally {
			session.close();
		}
		return dto;
	}

	/**
	 * find record from database with roll number
	 */

	public ProjectDTO findByLogin(String name) throws ApplicationException {
		Session session = null;
		ProjectDTO dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(ProjectDTO.class);
			criteria.add(Restrictions.eq("name", name));
			List list = criteria.list();
			if (list.size() > 0) {
				dto = (ProjectDTO) list.get(0);

			}
		} catch (Exception e) {

			throw new ApplicationException("Exception in getting Project by pk" + e.getMessage());

		} finally {
			session.close();
		}
		return dto;
	}

}
