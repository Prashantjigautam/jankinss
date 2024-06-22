package in.co.rays.project_3.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto.ContactDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.ContactModelInt;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(urlPatterns = { "/ctl/ContactCtl" })
public class ContactCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(ContactCtl.class);

	protected void preload(HttpServletRequest request) {
		ContactModelInt model = ModelFactory.getInstance().getContactModel();

		try {
			List li = model.list(0, 0);
			request.setAttribute("Contactlist", li);
			System.out.println("add Contact" + li);

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
	}

	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;
		System.out.println("validate started");
		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", " Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("name"))) {
			request.setAttribute("name", " name must contains alphabets only");
			pass = false;

		}
		if (DataValidator.isNull(request.getParameter("mobile"))) {
			request.setAttribute("mobile", PropertyReader.getValue("error.require", " mobile"));
			pass = false;
		} else if (!DataValidator.isPhoneNo(request.getParameter("mobile"))) {
			request.setAttribute("mobile", "Please Enter Valid Mobile Number");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("city"))) {
			request.setAttribute("city", PropertyReader.getValue("error.require", " city"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("city"))) {
			request.setAttribute("city", " city must contains alphabets only");
			pass = false;

		}

		if (DataValidator.isNull(request.getParameter("dob"))) {
			request.setAttribute("dob", PropertyReader.getValue("error.require", "dob"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("dob"))) {
			request.setAttribute("dob", PropertyReader.getValue("error.date", "Date Of Birth"));
			System.out.println(request.getParameter("dob"));
			pass = false;
		} else if (!DataValidator.isValidAge(request.getParameter("dob"))) {
			request.setAttribute("dob", "Age Must be greater then 18 year");
			pass = false;
		}
		System.out.println("validate end " + pass);

		return pass;
	}

	protected BaseDTO populateDTO(HttpServletRequest request) {
		log.debug("marksheet populate bean start");
		ContactDTO dto = new ContactDTO();
		String id = request.getParameter("Id");
		dto.setName(DataUtility.getString(request.getParameter("name")));
		dto.setMobile(DataUtility.getString(request.getParameter("mobile")));
		dto.setCity(DataUtility.getString(request.getParameter("city")));
		dto.setDob(DataUtility.getDate(request.getParameter("dob")));

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
		ContactModelInt model = ModelFactory.getInstance().getContactModel();
		if (id > 0) {
			ContactDTO dto;
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

		ContactModelInt model = ModelFactory.getInstance().getContactModel();

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
			ContactDTO dto = (ContactDTO) populateDTO(request);
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
				ServletUtility.setErrorMessage("Contact already exists", request);
			}

		} else if (OP_DELETE.equalsIgnoreCase(op)) {
			ContactDTO dto = (ContactDTO) populateDTO(request);
			try {
				model.delete(dto);
				ServletUtility.redirect(ORSView.CONTACT_LIST_CTL, request, response);
				return;
			} catch (ApplicationException e) {
				System.out.println("in catch");
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.CONTACT_CTL, request, response);
			return;
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.CONTACT_LIST_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

		log.debug("marksheet ctl dopost  end");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.CONTACT_VIEW;
	}

}