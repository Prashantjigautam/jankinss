
package in.co.rays.project_3.model;

import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * ModelFactory decides which model implementation run it is a factory of all
 * model
 * 
 * @author Prashant gautam
 *
 */
public final class ModelFactory {

	private static ResourceBundle rb = ResourceBundle.getBundle("in.co.rays.project_3.bundle.system");
	private static final String DATABASE = rb.getString("DATABASE");

	private static ModelFactory mFactory = null;
	private static HashMap modelCache = new HashMap();

	private ModelFactory() {

	}

	public static ModelFactory getInstance() {
		if (mFactory == null) {
			mFactory = new ModelFactory();
		}
		return mFactory;
	}

	public MarksheetModelInt getMarksheetModel() {
		MarksheetModelInt marksheetModel = (MarksheetModelInt) modelCache.get("marksheetModel");
		if (marksheetModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				marksheetModel = new MarksheetModelHibImp();
			}
			if ("JDBC".equals(DATABASE)) {
				marksheetModel = new MarksheetModelJDBCImpl();
			}
			modelCache.put("marksheetModel", marksheetModel);
		}
		return marksheetModel;
	}

	public CollegeModelInt getCollegeModel() {
		CollegeModelInt collegeModel = (CollegeModelInt) modelCache.get("collegeModel");
		if (collegeModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				collegeModel = new CollegeModelHibImp();

			}
			if ("JDBC".equals(DATABASE)) {
				collegeModel = new CollegeModelJDBCImpl();
			}
			modelCache.put("collegeModel", collegeModel);
		}
		return collegeModel;
	}

	public RoleModelInt getRoleModel() {
		RoleModelInt roleModel = (RoleModelInt) modelCache.get("roleModel");
		if (roleModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				roleModel = new RoleModelHibImp();

			}
			if ("JDBC".equals(DATABASE)) {
				roleModel = new RoleModelJDBCImpl();
			}
			modelCache.put("roleModel", roleModel);
		}
		return roleModel;
	}

	public UserModelInt getUserModel() {

		UserModelInt userModel = (UserModelInt) modelCache.get("userModel");
		if (userModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				userModel = new UserModelHibImp();
			}
			if ("JDBC".equals(DATABASE)) {
				userModel = new UserModelJDBCImpl();
			}
			modelCache.put("userModel", userModel);
		}

		return userModel;
	}

	public StudentModelInt getStudentModel() {
		StudentModelInt studentModel = (StudentModelInt) modelCache.get("studentModel");
		if (studentModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				studentModel = new StudentModelHibImp();
			}
			if ("JDBC".equals(DATABASE)) {
				studentModel = new StudentModelJDBCImpl();
			}
			modelCache.put("studentModel", studentModel);
		}

		return studentModel;
	}

	public CourseModelInt getCourseModel() {
		CourseModelInt courseModel = (CourseModelInt) modelCache.get("courseModel");
		if (courseModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				courseModel = new CourseModelHibImp();
			}
			if ("JDBC".equals(DATABASE)) {
				courseModel = new CourseModelJDBCImpl();
			}
			modelCache.put("courseModel", courseModel);
		}

		return courseModel;
	}

	public TimetableModelInt getTimetableModel() {

		TimetableModelInt timetableModel = (TimetableModelInt) modelCache.get("timetableModel");

		if (timetableModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				timetableModel = new TimetableModelHibImp();
			}
			if ("JDBC".equals(DATABASE)) {
				timetableModel = new TimetableModelJDBCImpl();
			}
			modelCache.put("timetableModel", timetableModel);
		}

		return timetableModel;
	}

	public SubjectModelInt getSubjectModel() {
		SubjectModelInt subjectModel = (SubjectModelInt) modelCache.get("subjectModel");
		if (subjectModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				subjectModel = new SubjectModelHibImp();
			}
			if ("JDBC".equals(DATABASE)) {
				subjectModel = new SubjectModelJDBCImpl();
			}
			modelCache.put("subjectModel", subjectModel);
		}

		return subjectModel;
	}

	public FacultyModelInt getFacultyModel() {
		FacultyModelInt facultyModel = (FacultyModelInt) modelCache.get("facultyModel");
		if (facultyModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				facultyModel = new FacultyModelHibImp();
			}
			if ("JDBC".equals(DATABASE)) {
				facultyModel = new FacultyModelJDBCImpl();
			}
			modelCache.put("facultyModel", facultyModel);
		}

		return facultyModel;
	}

	public BankModelInt getBankModel() {
		BankModelInt BankModel = (BankModelInt) modelCache.get("BankModel");
		if (BankModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				BankModel = new BankModelHibImp();
			}
			/*
			 * if ("JDBC".equals(DATABASE)) { BankModel = new BankModelJDBCImpl(); }
			 */
			modelCache.put("BankModel", BankModel);
		}

		return BankModel;
	}

	public OrderModelInt getOrderModel() {
		OrderModelInt OrderModel = (OrderModelInt) modelCache.get("OrderModel");
		if (OrderModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				OrderModel = new OrderModelHibImp();
			}
			modelCache.put("OrderModel", OrderModel);
		}

		return OrderModel;
	}

	public FollowUpModelInt getFollowUpModel() {
		FollowUpModelInt FollowUpModel = (FollowUpModelInt) modelCache.get("FollowUpModel");
		if (FollowUpModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				FollowUpModel = (FollowUpModelInt) new FollowUpModelHibImpl();
			}
			modelCache.put("FollowUpModel", FollowUpModel);
		}

		return FollowUpModel;

	}

	public PatientModelInt getPatientModel() {
		PatientModelInt PatientModel = (PatientModelInt) modelCache.get("PatientModel");
		if (PatientModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				PatientModel = (PatientModelInt) new PatientModelHibImpl();
			}
			modelCache.put("PatientModel", PatientModel);
		}

		return PatientModel;

	}

	public ContactModelInt getContactModel() {
		ContactModelInt ContactModel = (ContactModelInt) modelCache.get("ContactModel");
		if (ContactModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				ContactModel = (ContactModelInt) new ContactModelHibImpl();
			}
			modelCache.put("ContactModel", ContactModel);
		}

		return ContactModel;

	}

	public LeadModelInt getLeadModel() {
		LeadModelInt LeadModel = (LeadModelInt) modelCache.get("LeadModel");
		if (LeadModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				LeadModel = (LeadModelInt) new LeadModelHibImpl();
			}
			modelCache.put("LeadModel", LeadModel);
		}

		return LeadModel;

	}

	public WishListModelInt getWishListModel() {
		WishListModelInt WishListModel = (WishListModelInt) modelCache.get("WishListModel");
		if (WishListModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				WishListModel = (WishListModelInt) new WishListModelHibImpl();
			}
			modelCache.put("WishListModel", WishListModel);
		}

		return WishListModel;

	}

	public PrescriptionModelInt getPrescriptionModel() {
		PrescriptionModelInt PrescriptionModel = (PrescriptionModelInt) modelCache.get("PrescriptionModel");
		if (PrescriptionModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				PrescriptionModel = (PrescriptionModelInt) new PrescriptionModelHibImpl();
			}
			modelCache.put("PrescriptionModel", PrescriptionModel);
		}

		return PrescriptionModel;

	}

	public DoctorModelInt getDoctorModel() {
		DoctorModelInt DoctorModel = (DoctorModelInt) modelCache.get("DoctorModel");
		if (DoctorModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				DoctorModel = (DoctorModelInt) new DoctorModelHibImpl();
			}
			modelCache.put("DoctorModel", DoctorModel);
		}

		return DoctorModel;
	}

	public ShoppingCardModelInt getShoppingCardModel() {
		ShoppingCardModelInt ShoppingCardModel = (ShoppingCardModelInt) modelCache.get("ShoppingCardModel");
		if (ShoppingCardModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				ShoppingCardModel = (ShoppingCardModelInt) new ShoppingCardModelHibImpl();
			}
			modelCache.put("ShoppingCardModel", ShoppingCardModel);
		}

		return ShoppingCardModel;
	}

	public ProductDetailsModelInt getProductDetailsModel() {
		ProductDetailsModelInt ProductDetailsModel = (ProductDetailsModelInt) modelCache.get("ProductDetailsModel");
		if (ProductDetailsModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				ProductDetailsModel = (ProductDetailsModelInt) new ProductDetailsModelHibImpl();
			}
			modelCache.put("ProductDetailsModel", ProductDetailsModel);
		}

		return ProductDetailsModel;
	}

	public IssueModelInt getIssueModel() {
		IssueModelInt IssueModel = (IssueModelInt) modelCache.get("IssueModel");
		if (IssueModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				IssueModel = (IssueModelInt) new IssueModelHibImpl();
			}
			modelCache.put("IssueModel", IssueModel);
		}

		return IssueModel;
	}

	public ProjectModelInt getProjectModel() {
		ProjectModelInt ProjectModel = (ProjectModelInt) modelCache.get("ProjectModel");
		if (ProjectModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				ProjectModel = (ProjectModelInt) new ProjectModelHibImpl();
			}
			modelCache.put("ProjectModel", ProjectModel);
		}

		return ProjectModel;
	}

	public SupplierModelInt getSupplierModel() {
		SupplierModelInt SupplierModel = (SupplierModelInt) modelCache.get("SupplierModel");
		if (SupplierModel == null) {
			if ("Hibernate".equals(DATABASE)) {
				SupplierModel = (SupplierModelInt) new SupplierModelHibImpl();
			}
			modelCache.put("SupplierModel", SupplierModel);
		}

		return SupplierModel;
	}
}
