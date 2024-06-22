package in.co.rays.project_3.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.project_3.dto.BaseDTO;
import in.co.rays.project_3.dto.WishListDTO;
import in.co.rays.project_3.exception.ApplicationException;
import in.co.rays.project_3.exception.DuplicateRecordException;
import in.co.rays.project_3.model.ModelFactory;
import in.co.rays.project_3.model.WishListModelInt;
import in.co.rays.project_3.util.DataUtility;
import in.co.rays.project_3.util.DataValidator;
import in.co.rays.project_3.util.PropertyReader;
import in.co.rays.project_3.util.ServletUtility;

@WebServlet(urlPatterns = { "/ctl/WishListCtl" })
public class WishListCtl extends BaseCtl {

	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger(WishListCtl.class);

	protected void preload(HttpServletRequest request) {
		WishListModelInt model = ModelFactory.getInstance().getWishListModel();

		try {
			List li = model.list(0, 0);
			request.setAttribute("WishListlist", li);
			System.out.println("add WishList" + li);

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
	}

	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;
		System.out.println("validate started");
		if (DataValidator.isNull(request.getParameter("username"))) {
			request.setAttribute("username", PropertyReader.getValue("error.require", " username"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("username"))) {
			request.setAttribute("username", " username must contains alphabets only");
			pass = false;

		}
		if (DataValidator.isNull(request.getParameter("remark"))) {
			request.setAttribute("remark", PropertyReader.getValue("error.require", " remark"));
			pass = false;
		}  else if (!DataValidator.isName(request.getParameter("remark"))) {
			request.setAttribute("remark", " remark must contains alphabets only");
			pass = false;

		}
		if (DataValidator.isNull(request.getParameter("product"))) {
			request.setAttribute("product", PropertyReader.getValue("error.require", " product"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("product"))) {
			request.setAttribute("product", " product must contains alphabets only");
			pass = false;

		}

		if (DataValidator.isNull(request.getParameter("date"))) {
			request.setAttribute("date", PropertyReader.getValue("error.require", "date"));
			pass = false;
		} else if (!DataValidator.isDate(request.getParameter("date"))) {
			request.setAttribute("date", PropertyReader.getValue("error.date", "Date Of Birth"));
			System.out.println(request.getParameter("date"));
			pass = false;
		} else if (!DataValidator.isValidAge(request.getParameter("date"))) {
			request.setAttribute("date", "Age Must be greater then 18 year");
			pass = false;
		}
		System.out.println("validate end " + pass);

		return pass;
	}

	protected BaseDTO populateDTO(HttpServletRequest request) {
		log.debug("marksheet populate bean start");
		WishListDTO dto = new WishListDTO();
		String id = request.getParameter("Id");
		dto.setUsername(DataUtility.getString(request.getParameter("username")));
		dto.setRemark(DataUtility.getString(request.getParameter("remark")));
		dto.setProduct(DataUtility.getString(request.getParameter("product")));
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
		WishListModelInt model = ModelFactory.getInstance().getWishListModel();
		if (id > 0) {
			WishListDTO dto;
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

		WishListModelInt model = ModelFactory.getInstance().getWishListModel();

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
			WishListDTO dto = (WishListDTO) populateDTO(request);
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
				ServletUtility.setErrorMessage("WishList already exists", request);
			}

		} else if (OP_DELETE.equalsIgnoreCase(op)) {
			WishListDTO dto = (WishListDTO) populateDTO(request);
			try {
				model.delete(dto);
				ServletUtility.redirect(ORSView.WISHLIST_LIST_CTL, request, response);
				return;
			} catch (ApplicationException e) {
				System.out.println("in catch");
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.WISHLIST_CTL, request, response);
			return;
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.WISHLIST_LIST_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

		log.debug("marksheet ctl dopost  end");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.WISHLIST_VIEW;
	}

}
