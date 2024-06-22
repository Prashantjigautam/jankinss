package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.FollowUpDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface FollowUpModelInt {
	public long add(FollowUpDTO dto) throws ApplicationException, DuplicateRecordException;

	public void delete(FollowUpDTO dto) throws ApplicationException;

	public void update(FollowUpDTO dto) throws ApplicationException, DuplicateRecordException;

	public FollowUpDTO findByPK(long pk) throws ApplicationException;

	public FollowUpDTO findByLogin(String name) throws ApplicationException;

	public List search(FollowUpDTO dto, int pageNo, int pageSize) throws ApplicationException;

	public List list(int pageNo, int pageSize) throws ApplicationException;
}
