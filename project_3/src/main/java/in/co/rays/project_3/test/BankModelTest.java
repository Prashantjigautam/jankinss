package in.co.rays.project_3.test;

import in.co.rays.project_3.dto.BankDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.BankModelInt;
import in.co.rays.project_3.model.ModelFactory;

public class BankModelTest {
	public static void main(String[] args) throws Exception {
		testadd();
	}

	private static void testadd() throws Exception {
		BankDTO dto = new BankDTO();
		dto.setAccountNo("12354533");
		dto.setName("prashant");
		BankModelInt model = ModelFactory.getInstance().getBankModel();
		model.add(dto);
		System.out.println("data added ");
	}

}
