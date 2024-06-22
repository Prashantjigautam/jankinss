package in.co.rays.project_3.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto.LeadDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.LeadModelInt;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(urlPatterns = { "/ctl/LeadCtl" })
public class LeadCtl extends BaseCtl {

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(LeadCtl.class);

	protected void preload(HttpServletRequest request) {
		HashMap map = new HashMap();
		map.put("Hot", "Hot");
		map.put("Warm", "Warm ");
		map.put("Cold", "Cold");

		request.setAttribute("leadList", map);
	}

	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;
		System.out.println("validate started");
		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", " contactName"));
			pass = false;
		} else if (!DataValidator.isNamemaxlength(request.getParameter("name"))) {
			request.setAttribute("name", "contactName should be 20 characters");
			pass = false;

		}

		else if (!DataValidator.isName(request.getParameter("name"))) {
			request.setAttribute("name", " contactName must contains alphabets only");
			pass = false;
		}
		/*
		 * if (DataValidator.isNull(request.getParameter("mobile"))) {
		 * request.setAttribute("mobile", PropertyReader.getValue("error.require",
		 * " mobile")); pass = false; } else if
		 * (!DataValidator.isPhoneNo(request.getParameter("mobile"))) {
		 * request.setAttribute("mobile", "Please Enter Valid Mobile Number"); pass =
		 * false;
		 * 
		 * }
		 */ if (DataValidator.isNull(request.getParameter("mobile"))) {
			request.setAttribute("mobile", PropertyReader.getValue("error.require", "MobileNo"));
			pass = false;

		} else if (!DataValidator.isPhoneLength(request.getParameter("mobile"))) {
			request.setAttribute("mobile", "mobile no. should be 10 digits");
			pass = false;
		}

		else if (!DataValidator.isPhoneNo(request.getParameter("mobile"))) {
			request.setAttribute("mobile", "Mobile No. must be start with 6-9");
			pass = false;
		}
		/*
		 * } else if (!DataValidator.isNamemaxlength(request.getParameter("name"))) {
		 * request.setAttribute("mobile", "Mobile Number should be 10 characters"); pass
		 * = false;
		 * 
		 * }
		 */

		if (DataValidator.isNull(request.getParameter("status"))) {
			request.setAttribute("status", PropertyReader.getValue("error.require", " status"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("date"))) {
			request.setAttribute("date", PropertyReader.getValue("error.require", "date"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("date"))) {
			request.setAttribute("date", PropertyReader.getValue("error.date", "Date "));
			System.out.println(request.getParameter("date"));
			pass = false;
		}
		System.out.println("validate end " + pass);

		return pass;
	}

	protected BaseDTO populateDTO(HttpServletRequest request) {
		log.debug("marksheet populate bean start");
		LeadDTO dto = new LeadDTO();
		String id = request.getParameter("Id");
		dto.setName(DataUtility.getString(request.getParameter("name")));
		dto.setMobile(DataUtility.getString(request.getParameter("mobile")));
		dto.setStatus(DataUtility.getString(request.getParameter("status")));
		dto.setDate(DataUtility.getDate(request.getParameter("date")));

		populateBean(dto, request);
		log.debug("marksheet populate bean end");
		return dto;

	}

	/**
	 * Display Logics inside this method
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		log.debug("marksheet ctl doget  start");
		long id = DataUtility.getLong(request.getParameter("id"));
		LeadModelInt model = ModelFactory.getInstance().getLeadModel();
		if (id > 0) {
			LeadDTO dto;
			try {
				dto = model.findByPK(id);
				ServletUtility.setDto(dto, request);
			} catch (ApplicationException e) {
				e.printStackTrace();
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);
		log.debug("marksheet ctl doget  end");
	}

	/**
	 * Submit logic inside it
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		log.debug("marksheet ctl dopost  start");

		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));

		LeadModelInt model = ModelFactory.getInstance().getLeadModel();

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
			LeadDTO dto = (LeadDTO) populateDTO(request);
			try {
				if (id > 0) {
					dto.setId(id);
					model.update(dto);
					ServletUtility.setSuccessMessage("Data is successfully Updated", request);
				} else {
					model.add(dto);
					ServletUtility.setSuccessMessage("Data is successfully saved", request);
				}
				ServletUtility.setDto(dto, request);

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage("Lead already exists", request);
			}

		} else if (OP_DELETE.equalsIgnoreCase(op)) {
			LeadDTO dto = (LeadDTO) populateDTO(request);
			try {
				model.delete(dto);
				ServletUtility.redirect(ORSView.LEAD_LIST_CTL, request, response);
				return;
			} catch (ApplicationException e) {
				System.out.println("in catch");
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.LEAD_CTL, request, response);
			return;
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.LEAD_LIST_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

		log.debug("marksheet ctl dopost  end");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.LEAD_VIEW;
	}

}
