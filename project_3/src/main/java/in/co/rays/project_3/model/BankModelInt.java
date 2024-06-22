package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.BankDTO;
import in.co.rays.project_3.dto.OrderDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface BankModelInt {

	public long add(BankDTO dto) throws ApplicationException, DuplicateRecordException;

	public void delete(BankDTO dto) throws ApplicationException;

	public void update(BankDTO dto) throws ApplicationException, DuplicateRecordException;

	//public OrderDTO findByPK(long pk) throws ApplicationException;

	public BankDTO findByLogin(String login) throws ApplicationException;

	public List search(BankDTO dto, int pageNo, int pageSize) throws ApplicationException;
}
