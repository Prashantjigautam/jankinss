package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.ShoppingCardDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface ShoppingCardModelInt {
	public long add(ShoppingCardDTO dto) throws ApplicationException, DuplicateRecordException;

	public void delete(ShoppingCardDTO dto) throws ApplicationException;

	public void update(ShoppingCardDTO dto) throws ApplicationException, DuplicateRecordException;

	public ShoppingCardDTO findByPK(long pk) throws ApplicationException;

	public ShoppingCardDTO findByLogin(String name) throws ApplicationException;

	public List search(ShoppingCardDTO dto, int pageNo, int pageSize) throws ApplicationException;

	public List list(int pageNo, int pageSize) throws ApplicationException;
}



