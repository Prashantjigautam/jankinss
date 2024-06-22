package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.ProjectDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface ProjectModelInt {
	public long add(ProjectDTO dto) throws ApplicationException, DuplicateRecordException;

	public void delete(ProjectDTO dto) throws ApplicationException;

	public void update(ProjectDTO dto) throws ApplicationException, DuplicateRecordException;

	public ProjectDTO findByPK(long pk) throws ApplicationException;

	public ProjectDTO findByLogin(String name) throws ApplicationException;

	public List search(ProjectDTO dto, int pageNo, int pageSize) throws ApplicationException;

	public List list(int pageNo, int pageSize) throws ApplicationException;
}
