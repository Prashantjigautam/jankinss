package in.co.rays.project_3.model;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project_3.dto.OrderDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class OrderModelHibImp implements OrderModelInt {

	public long add(OrderDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		Session session = HibDataSource.getSession();
		Transaction tx = null;

		// get Student product

		/*OrderDTO duplicateOrder = findByLogin(dto.getProduct());

		if (duplicateOrder != null) {
			throw new DuplicateRecordException("Order is  already exists");
		}*/

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
			throw new ApplicationException("Exception in Order Add " + e.getMessage());
		} finally {
			session.close();
		}
		return pk;
	}

	/**
	 * delete record from database
	 */
	public void delete(OrderDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		OrderDTO dtoExist = findByPK(dto.getId());
		if (dtoExist == null) {
			throw new ApplicationException("Order does not exist");
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
			throw new ApplicationException("Exception in Order Delete" + e.getMessage());
		} finally {
			session.close();
		}
	}

	/**
	 * update record in database
	 */
	public void update(OrderDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
/*
		OrderDTO dtoExist = findByLogin(dto.getProduct());

		// Check if updated Roll no already exist

		if (dtoExist != null && dtoExist.getId() != dto.getId()) {
			throw new DuplicateRecordException(" Order is already exist");
		}*/

		// get Student product
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
				throw new ApplicationException("Exception in Order Update" + e.getMessage());
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
			Criteria criteria = session.createCriteria(OrderDTO.class);
			if (pageSize > 0) {

				pageNo = ((pageNo - 1) * pageSize) + 1;

				criteria.setFirstResult(pageNo);
				criteria.setMaxResults(pageSize);
			}
			list = criteria.list();

		} catch (Exception e) {

			throw new ApplicationException("Exception in  Order List" + e.getMessage());
		} finally {
			session.close();
		}
		return list;
	}

	/**
	 * search record from database
	 */
	public List search(OrderDTO dto, int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		List list = null;
		
		
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(OrderDTO.class);
			/*
			 * if (dto.getId() > 0) { criteria.add(Restrictions.eq("id", dto.getId()));
			 * 
			 */
			if (dto != null) {
				if (dto.getProduct() != null && dto.getProduct().length() > 0) {
					criteria.add(Restrictions.like("product", dto.getProduct() + "%"));
				}
				if (dto.getQuantity() != null && dto.getQuantity().length() > 0) {
					criteria.add(Restrictions.like("quantity", dto.getQuantity() + "%"));
				}
				if (dto.getDate() != null && dto.getDate().getDate() > 0) {
					criteria.add(Restrictions.eq("date", dto.getDate()));
				}
				if (dto.getAmmount() != null && dto.getAmmount().length() > 0) {
					criteria.add(Restrictions.like("ammount", dto.getAmmount() + "%"));
				}
			}

			if (pageSize > 0) {
				criteria.setFirstResult((pageNo - 1) * pageSize);
				criteria.setMaxResults(pageSize);
			}
			list = criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in Order Search " + e.getMessage());
		} finally {
			session.close();
		}
		return list;
	}

	/**
	 * find record from database with non business primary key
	 */
	public OrderDTO findByPK(long pk) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		OrderDTO dto = null;
		try {
			session = HibDataSource.getSession();
			dto = (OrderDTO) session.get(OrderDTO.class, pk);

		} catch (Exception e) {

			throw new ApplicationException("Exception in getting Order by pk" + e.getMessage());

		} finally {
			session.close();
		}
		return dto;
	}

	/**
	 * find record from database with roll number
	 */

	public OrderDTO findByLogin(String product) throws ApplicationException {
		Session session = null;
		OrderDTO dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(OrderDTO.class);
			criteria.add(Restrictions.eq("product", product));
			List list = criteria.list();
			if (list.size() > 0) {
				dto = (OrderDTO) list.get(0);

			}
		} catch (Exception e) {

			throw new ApplicationException("Exception in getting Order by pk" + e.getMessage());

		} finally {
			session.close();
		}
		return dto;
	}

}


