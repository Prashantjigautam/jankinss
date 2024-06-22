package in.co.rays.project_3.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto.FollowUpDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.FollowUpModelInt;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(urlPatterns = { "/ctl/FollowUpCtl" })
public class FollowUpCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(FollowUpCtl.class);

	protected void preload(HttpServletRequest request) {
		FollowUpModelInt model = ModelFactory.getInstance().getFollowUpModel();
		try {
			List li = model.list(0, 0);
			request.setAttribute("FollowUplist", li);
			System.out.println("add FollowUp" + li);

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
	}

	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;
		System.out.println("validate started");
		if (DataValidator.isNull(request.getParameter("doctor"))) {
			request.setAttribute("doctor", PropertyReader.getValue("error.require", " doctor"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("doctor"))) {
			request.setAttribute("doctor", " doctor must contains alphabets only");
			pass = false;

		}
		if (DataValidator.isNull(request.getParameter("patient"))) {
			request.setAttribute("patient", PropertyReader.getValue("error.require", " patient"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("doctor"))) {
			request.setAttribute("patient", " patient must contains alphabets only");
			pass = false;

		}
	
		if (DataValidator.isNull(request.getParameter("fees"))) {
			request.setAttribute("fees", PropertyReader.getValue("error.require", " fees"));
			pass = false;
		} else if (!DataValidator.isInteger(request.getParameter("fees"))) {
			request.setAttribute("fees", " please enter a valid fees");
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("dov"))) {
			request.setAttribute("dov", PropertyReader.getValue("error.require", "dov"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("dov"))) {
			request.setAttribute("dov", PropertyReader.getValue("error.date", "Date Of Birth"));
			System.out.println(request.getParameter("dov"));
			pass = false;
		} else if (!DataValidator.isValidAge(request.getParameter("dov"))) {
			request.setAttribute("dov", "Age Must be greater then 18 year");
			pass = false;
		}
		System.out.println("validate end " + pass);

		return pass;
	}

	protected BaseDTO populateDTO(HttpServletRequest request) {
		log.debug("marksheet populate bean start");
		FollowUpDTO dto = new FollowUpDTO();
		String id = request.getParameter("Id");
		dto.setDoctor(DataUtility.getString(request.getParameter("doctor")));
		dto.setPatient(DataUtility.getString(request.getParameter("patient")));
		dto.setFees(DataUtility.getString(request.getParameter("fees")));
		dto.setDov(DataUtility.getDate(request.getParameter("dov")));

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
		FollowUpModelInt model = ModelFactory.getInstance().getFollowUpModel();
		if (id > 0) {
			FollowUpDTO dto;
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

		FollowUpModelInt model = ModelFactory.getInstance().getFollowUpModel();

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
			FollowUpDTO dto = (FollowUpDTO) populateDTO(request);
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
				ServletUtility.setErrorMessage("FollowUp already exists", request);
			}

		} else if (OP_DELETE.equalsIgnoreCase(op)) {
			FollowUpDTO dto = (FollowUpDTO) populateDTO(request);
			try {
				model.delete(dto);
				ServletUtility.redirect(ORSView.FOLLOWUP_LIST_CTL, request, response);
				return;
			} catch (ApplicationException e) {
				System.out.println("in catch");
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.FOLLOWUP_CTL, request, response);
			return;
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.FOLLOWUP_LIST_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

		log.debug("marksheet ctl dopost  end");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.FOLLOWUP_VIEW;
	}

}
