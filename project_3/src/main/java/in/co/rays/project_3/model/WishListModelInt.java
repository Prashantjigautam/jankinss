package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.WishListDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface WishListModelInt  {
	public long add(WishListDTO dto) throws ApplicationException, DuplicateRecordException;

	public void delete(WishListDTO dto) throws ApplicationException;

	public void update(WishListDTO dto) throws ApplicationException, DuplicateRecordException;

	public WishListDTO findByPK(long pk) throws ApplicationException;

	public WishListDTO findByLogin(String name) throws ApplicationException;

	public List search(WishListDTO dto, int pageNo, int pageSize) throws ApplicationException;

	public List list(int pageNo, int pageSize) throws ApplicationException;
}



