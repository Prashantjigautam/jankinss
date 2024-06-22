package in.co.rays.project_3.model;

import java.util.List;

import in.co.rays.project_3.dto.DoctorDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;

public interface DoctorModelInt {
	public long add(DoctorDTO dto) throws ApplicationException, DuplicateRecordException;

	public void delete(DoctorDTO dto) throws ApplicationException;

	public void update(DoctorDTO dto) throws ApplicationException, DuplicateRecordException;

	public DoctorDTO findByPK(long pk) throws ApplicationException;

	public DoctorDTO findByLogin(String name) throws ApplicationException;

	public List search(DoctorDTO dto, int pageNo, int pageSize) throws ApplicationException;

	public List list(int pageNo, int pageSize) throws ApplicationException;
}



