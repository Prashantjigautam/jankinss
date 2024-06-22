package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.LeadDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface LeadModelInt {
	public long add(LeadDTO dto) throws ApplicationException, DuplicateRecordException;

	public void delete(LeadDTO dto) throws ApplicationException;

	public void update(LeadDTO dto) throws ApplicationException, DuplicateRecordException;

	public LeadDTO findByPK(long pk) throws ApplicationException;

	public LeadDTO findByLogin(String name) throws ApplicationException;

	public List search(LeadDTO dto, int pageNo, int pageSize) throws ApplicationException;

	public List list(int pageNo, int pageSize) throws ApplicationException;
}
