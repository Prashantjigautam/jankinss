package in.co.rays.project_3.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.ShoppingCardDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class ShoppingCardModelHibImpl implements ShoppingCardModelInt {
	public long add(ShoppingCardDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		Session session = HibDataSource.getSession();
		Transaction tx = null;

		// get Student Name

		ShoppingCardDTO duplicateShoppingCard = findByLogin(dto.getName());

		if (duplicateShoppingCard != null) {
			throw new DuplicateRecordException("ShoppingCard is  already exists");
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
			throw new ApplicationException("Exception in ShoppingCard Add " + e.getMessage());
		} finally {
			session.close();
		}
		return pk;
	}

	/**
	 * delete record from database
	 */
	public void delete(ShoppingCardDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		ShoppingCardDTO dtoExist = findByPK(dto.getId());
		if (dtoExist == null) {
			throw new ApplicationException("ShoppingCard does not exist");
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
			throw new ApplicationException("Exception in ShoppingCard Delete" + e.getMessage());
		} finally {
			session.close();
		}
	}

	/**
	 * update record in database
	 */
	public void update(ShoppingCardDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;

		ShoppingCardDTO dtoExist = findByLogin(dto.getName());

		// Check if updated Roll no already exist

		if (dtoExist != null && dtoExist.getId() != dto.getId()) {
			throw new DuplicateRecordException(" ShoppingCard is already exist");
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
				throw new ApplicationException("Exception in ShoppingCard Update" + e.getMessage());
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
			Criteria criteria = session.createCriteria(ShoppingCardDTO.class);
			if (pageSize > 0) {

				pageNo = ((pageNo - 1) * pageSize) + 1;

				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = criteria.list();

		} catch (Exception e) {

			throw new ApplicationException("Exception in  ShoppingCard List" + e.getMessage());
		} finally {
			session.close();
		}
		return list;
	}

	/**
	 * search record from database
	 */
	public List search(ShoppingCardDTO dto, int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
				Session session = null;
				List list = null;

				try {
					session = HibDataSource.getSession();
					Criteria criteria = session.createCriteria(ShoppingCardDTO.class);
					/*
					 * if (dto.getId() > 0) { criteria.add(Restrictions.eq("id", dto.getId()));
					 * 
					 */
					if (dto != null) {
						if (dto.getName() != null && dto.getName().length() > 0) {
							criteria.add(Restrictions.like("name", dto.getName() + "%"));
						}
						if (dto.getProduct() != null && dto.getProduct().length() > 0) {
							criteria.add(Restrictions.like("product", dto.getProduct() + "%"));
						}
						if (dto.getDate() != null && dto.getDate().getDate() > 0) {
							criteria.add(Restrictions.eq("date", dto.getDate()));
						}
						if (dto.getQuantity() != null && dto.getQuantity().length() > 0) {
							criteria.add(Restrictions.like("quantity", dto.getQuantity() + "%"));
						}
					}

					if (pageSize > 0) {
						criteria.setFirstResult((pageNo - 1) * pageSize);
						criteria.setMaxResults(pageSize);
					}
					list = criteria.list();
				} catch (Exception e) {
					e.printStackTrace();
					throw new ApplicationException("Exception in ShoppingCard Search " + e.getMessage());
				} finally {
					session.close();
				}
				return list;
			}

	/**
	 * find record from database with non business primary key
	 */
	public ShoppingCardDTO findByPK(long pk) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		ShoppingCardDTO dto = null;
		try {
			session = HibDataSource.getSession();
			dto = (ShoppingCardDTO) session.get(ShoppingCardDTO.class, pk);

		} catch (Exception e) {

			throw new ApplicationException("Exception in getting ShoppingCard by pk" + e.getMessage());

		} finally {
			session.close();
		}
		return dto;
	}

	/**
	 * find record from database with roll number
	 */

	public ShoppingCardDTO findByLogin(String name) throws ApplicationException {
		Session session = null;
		ShoppingCardDTO dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(ShoppingCardDTO.class);
			criteria.add(Restrictions.eq("name", name));
			List list = criteria.list();
			if (list.size() > 0) {
				dto = (ShoppingCardDTO) list.get(0);

			}
		} catch (Exception e) {

			throw new ApplicationException("Exception in getting ShoppingCard by pk" + e.getMessage());

		} finally {
			session.close();
		}
		return dto;
	}

}
