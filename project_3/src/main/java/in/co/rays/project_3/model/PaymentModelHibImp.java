package in.co.rays.project_3.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;


import in.co.rays.project_3.dto.MarksheetDTO;

import in.co.rays.project_3.dto.PaymentDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.util.HibDataSource;

public class PaymentModelHibImp implements PaymentModelInt {

	@Override
	public long add(PaymentDTO dto) throws ApplicationException, DuplicateRecordException {

		Session session = HibDataSource.getSession();
		Transaction tx = null;
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

	public void delete(PaymentDTO dto) throws ApplicationException {
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

	public void update(PaymentDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		Session session = null;
		Transaction tx = null;
		// BankDTO existDto = findByLogin(dto.getLogin());
		// Check if updated LoginId already exist
		// if (existDto != null && existDto.getId() != dto.getId()) {
		// throw new DuplicateRecordException("LoginId is already exist");
		// }

		try {
			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(dto);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in User update" + e.getMessage());
		} finally {
			session.close();
		}
	}

	/*
	 * public long findByPK(long pk) throws ApplicationException { // TODO
	 * Auto-generated method stub Session session = null; BankDTO dto = null; try {
	 * session = HibDataSource.getSession(); dto = (BankDTO)
	 * session.get(BankDTO.class, pk);
	 * 
	 * } catch (HibernateException e) { throw new
	 * ApplicationException("Exception : Exception in getting User by pk"); }
	 * finally { session.close(); }
	 * 
	 * 
	 * }
	 */

	public PaymentDTO findByLogin(String login) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		PaymentDTO dto = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(PaymentDTO.class);
			criteria.add(Restrictions.eq("login", login));
			List list = criteria.list();
			if (list.size() == 1) {
				dto = (PaymentDTO) list.get(0);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ApplicationException("Exception in getting User by Login " + e.getMessage());

		} finally {
			session.close();
		}

		return dto;
	}

	public List search(PaymentDTO dto, int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria criteria = session.createCriteria(PaymentDTO.class);
			if(dto!=null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));

			}
			if (dto.getBank() != null && dto.getBank().length() > 0) {
				criteria.add(Restrictions.like("product", dto.getBank() + "%"));
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
	@Override
	public PaymentDTO findByPK(long pk) throws ApplicationException {
		// TODO Auto-generated method stub
		Session session = null;
		PaymentDTO dto = null;
		try {
			session = HibDataSource.getSession();
			dto = (PaymentDTO) session.get(PaymentDTO.class, pk);

		} catch (Exception e) {

			throw new ApplicationException("Exception in getting Marksheet by pk" + e.getMessage());

		} finally {
			session.close();
		}
		return dto;

	}
}
