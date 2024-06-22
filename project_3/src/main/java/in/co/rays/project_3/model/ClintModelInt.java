package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.ClintDto;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface ClintModelInt {
	public long add(ClintDto dto) throws ApplicationException, DuplicateRecordException;

	public void delete(ClintDto dto) throws ApplicationException;

	public void update(ClintDto dto) throws ApplicationException, DuplicateRecordException;

	 public ClintDto findByPK(long pk) throws ApplicationException;

	public ClintDto findByLogin(String name) throws ApplicationException;

	public List search(ClintDto dto, int pageNo, int pageSize) throws ApplicationException;

	public List list(int pageNo, int pageSize) throws ApplicationException;
}
