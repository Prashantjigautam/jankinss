package in.co.rays.project_3.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto.ProductDetailsDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.ProductDetailsModelInt;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(urlPatterns = { "/ctl/ProductDetailsCtl" })
public class ProductDetailsCtl extends BaseCtl {

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(ProductDetailsCtl.class);

	protected void preload(HttpServletRequest request) {
		ProductDetailsModelInt model = ModelFactory.getInstance().getProductDetailsModel();

		try {
			List li = model.list(0, 0);
			request.setAttribute("ProductDetailslist", li);
			System.out.println("add ProductDetails" + li);

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
		if (DataValidator.isNull(request.getParameter("price"))) {
			request.setAttribute("price", PropertyReader.getValue("error.require", " price"));
			pass = false;
		} else if (!DataValidator.isInteger(request.getParameter("price"))) {
			request.setAttribute("price", "Please Enter Valid price Number");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("category"))) {
			request.setAttribute("category", PropertyReader.getValue("error.require", " category"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("category"))) {
			request.setAttribute("category", " category must contains alphabets only");
			pass = false;

		}
		if (DataValidator.isNull(request.getParameter("decription"))) {
			request.setAttribute("decription", PropertyReader.getValue("error.require", " decription"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("decription"))) {
			request.setAttribute("decription", " decription must contains alphabets only");
			pass = false;

		}

		if (DataValidator.isNull(request.getParameter("dop"))) {
			request.setAttribute("dop", PropertyReader.getValue("error.require", "dop"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("dop"))) {
			request.setAttribute("dop", PropertyReader.getValue("error.date", "Date Of purchage"));
			System.out.println(request.getParameter("dop"));
			pass = false;
		} else if (!DataValidator.isValidAge(request.getParameter("dop"))) {
			request.setAttribute("dop", "Age Must be greater then 18 year");
			pass = false;
		}
		System.out.println("validate end " + pass);

		return pass;
	}

	protected BaseDTO populateDTO(HttpServletRequest request) {
		log.debug("marksheet populate bean start");
		ProductDetailsDTO dto = new ProductDetailsDTO();
		String id = request.getParameter("Id");
		dto.setName(DataUtility.getString(request.getParameter("name")));
		dto.setPrice(DataUtility.getString(request.getParameter("price")));
		dto.setCategory(DataUtility.getString(request.getParameter("category")));
		dto.setDop(DataUtility.getDate(request.getParameter("dop")));
		dto.setDecription(DataUtility.getString(request.getParameter("decription")));

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
		ProductDetailsModelInt model = ModelFactory.getInstance().getProductDetailsModel();
		if (id > 0) {
			ProductDetailsDTO dto;
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

		ProductDetailsModelInt model = ModelFactory.getInstance().getProductDetailsModel();

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
			ProductDetailsDTO dto = (ProductDetailsDTO) populateDTO(request);
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
				ServletUtility.setErrorMessage("ProductDetails already exists", request);
			}

		} else if (OP_DELETE.equalsIgnoreCase(op)) {
			ProductDetailsDTO dto = (ProductDetailsDTO) populateDTO(request);
			try {
				model.delete(dto);
				ServletUtility.redirect(ORSView.PRODUCTDETAILS_LIST_CTL, request, response);
				return;
			} catch (ApplicationException e) {
				System.out.println("in catch");
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.PRODUCTDETAILS_CTL, request, response);
			return;
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.PRODUCTDETAILS_LIST_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

		log.debug("marksheet ctl dopost  end");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.PRODUCTDETAILS_VIEW;
	}

}
