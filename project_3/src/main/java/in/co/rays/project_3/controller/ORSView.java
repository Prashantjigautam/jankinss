package in.co.rays.project_3.controller;

public interface ORSView {
	public String APP_CONTEXT = "/project_3";

	public String PAGE_FOLDER = "/jsp";

	public String JAVA_DOC_VIEW = APP_CONTEXT + "/doc/index.html";

	public String ERROR_VIEW = PAGE_FOLDER + "/ErrorView404.jsp";

	public String MARKSHEET_VIEW = PAGE_FOLDER + "/MarksheetView.jsp";

	public String MARKSHEET_LIST_VIEW = PAGE_FOLDER + "/MarksheetListView.jsp";
	public String GET_MARKSHEET_VIEW = PAGE_FOLDER + "/GetMarksheetView.jsp";
	public String USER_VIEW = PAGE_FOLDER + "/UserView.jsp";
	public String USER_LIST_VIEW = PAGE_FOLDER + "/UserListView.jsp";
	public String COLLEGE_VIEW = PAGE_FOLDER + "/CollegeView.jsp";
	public String COLLEGE_LIST_VIEW = PAGE_FOLDER + "/CollegeListView.jsp";
	public String STUDENT_VIEW = PAGE_FOLDER + "/StudentView.jsp";
	public String STUDENT_LIST_VIEW = PAGE_FOLDER + "/StudentListView.jsp";
	public String ROLE_VIEW = PAGE_FOLDER + "/RoleView.jsp";
	public String ROLE_LIST_VIEW = PAGE_FOLDER + "/RoleListView.jsp";
	public String USER_REGISTRATION_VIEW = PAGE_FOLDER + "/UserRegistrationView.jsp";
	public String LOGIN_VIEW = PAGE_FOLDER + "/LoginView.jsp";
	public String WELCOME_VIEW = PAGE_FOLDER + "/Welcome.jsp";
	public String CHANGE_PASSWORD_VIEW = PAGE_FOLDER + "/ChangePasswordView.jsp";
	public String MY_PROFILE_VIEW = PAGE_FOLDER + "/MyProfileView.jsp";
	public String FORGET_PASSWORD_VIEW = PAGE_FOLDER + "/ForgetPasswordView.jsp";
	public String MARKSHEET_MERIT_LIST_VIEW = PAGE_FOLDER + "/MarksheetMeritListView.jsp";

	public String FACULTY_VIEW = PAGE_FOLDER + "/FacultyView.jsp";
	public String FACULTY_LIST_VIEW = PAGE_FOLDER + "/FacultyListView.jsp";
	public String COURSE_VIEW = PAGE_FOLDER + "/CourseView.jsp";
	public String COURSE_LIST_VIEW = PAGE_FOLDER + "/CourseListView.jsp";
	public String TIMETContactLE_VIEW = PAGE_FOLDER + "/TimeTContactleView.jsp";
	public String TIMETContactLE_LIST_VIEW = PAGE_FOLDER + "/TimeTContactleListView.jsp";
	public String SUBJECT_VIEW = PAGE_FOLDER + "/SubjectView.jsp";
	public String SUBJECT_LIST_VIEW = PAGE_FOLDER + "/SubjectListView.jsp";

	public String ERROR_CTL = APP_CONTEXT + "/ErrorCtl";

	public String MARKSHEET_CTL = APP_CONTEXT + "/ctl/MarksheetCtl";
	public String MARKSHEET_LIST_CTL = APP_CONTEXT + "/ctl/MarksheetListCtl";
	public String USER_CTL = APP_CONTEXT + "/ctl/UserCtl";
	public String USER_LIST_CTL = APP_CONTEXT + "/ctl/UserListCtl";
	public String COLLEGE_CTL = APP_CONTEXT + "/ctl/CollegeCtl";
	public String COLLEGE_LIST_CTL = APP_CONTEXT + "/ctl/CollegeListCtl";
	public String STUDENT_CTL = APP_CONTEXT + "/ctl/StudentCtl";
	public String STUDENT_LIST_CTL = APP_CONTEXT + "/ctl/StudentListCtl";
	public String ROLE_CTL = APP_CONTEXT + "/ctl/RoleCtl";
	public String ROLE_LIST_CTL = APP_CONTEXT + "/ctl/RoleListCtl";
	public String USER_REGISTRATION_CTL = APP_CONTEXT + "/UserRegistrationCtl";
	public String LOGIN_CTL = APP_CONTEXT + "/LoginCtl";
	public String WELCOME_CTL = APP_CONTEXT + "/WelcomeCtl";

	public String FACULTY_CTL = APP_CONTEXT + "/ctl/FacultyCtl";
	public String FACULTY_LIST_CTL = APP_CONTEXT + "/ctl/FacultyListCtl";
	public String COURSE_CTL = APP_CONTEXT + "/ctl/CourseCtl";
	public String COURSE_LIST_CTL = APP_CONTEXT + "/ctl/CourseListCtl";
	public String SUBJECT_CTL = APP_CONTEXT + "/ctl/SubjectCtl";
	public String SUBJECT_LIST_CTL = APP_CONTEXT + "/ctl/SubjectListCtl";
	public String TIMETContactLE_CTL = APP_CONTEXT + "/ctl/TimeTContactleCtl";
	public String TIMETContactLE_LIST_CTL = APP_CONTEXT + "/ctl/TimeTContactleListCtl";

	public String GET_MARKSHEET_CTL = APP_CONTEXT + "/ctl/GetMarksheetCtl";
	public String CHANGE_PASSWORD_CTL = APP_CONTEXT + "/ctl/ChangePasswordCtl";
	public String MY_PROFILE_CTL = APP_CONTEXT + "/ctl/MyProfileCtl";
	public String FORGET_PASSWORD_CTL = APP_CONTEXT + "/ForgetPasswordCtl";
	public String MARKSHEET_MERIT_LIST_CTL = APP_CONTEXT + "/ctl/MarksheetMeritListCtl";

	public String BANK_CTL = APP_CONTEXT + "/ctl/BankCtl";

	public String BANK_LIST_CTL = APP_CONTEXT + "/ctl/BankListCtl";
	public String BANK_LIST_VIEW = PAGE_FOLDER + "/BankListView.jsp";

	public String BANK_VIEW = PAGE_FOLDER + "/BankView.jsp";

	public String ORDER_VIEW = PAGE_FOLDER + "/OrderView.jsp";
	public String ORDER_LIST_VIEW = PAGE_FOLDER + "/OrderListView.jsp";

	public String ORDER_CTL = APP_CONTEXT + "/ctl/OrderCtl";
	public String ORDER_LIST_CTL = APP_CONTEXT + "/ctl/OrderListCtl";

	public String CLINT_CTL = APP_CONTEXT + "/ctl/ClintCtl";
	public String CLINT_LIST_CTL = APP_CONTEXT + "/ctl/ClintListCtl";

	public String CLINT_VIEW = PAGE_FOLDER + "/ClintView.jsp";
	public String CLINT_LIST_VIEW = PAGE_FOLDER + "/ClintListView.jsp";

	public String FOLLOWUP_CTL = APP_CONTEXT + "/ctl/FollowUpCtl";
	public String FOLLOWUP_LIST_CTL = APP_CONTEXT + "/ctl/FollowUpListCtl";

	public String FOLLOWUP_VIEW = PAGE_FOLDER + "/FollowUpView.jsp";
	public String FOLLOWUP_LIST_VIEW = PAGE_FOLDER + "/FollowUpListView.jsp";
	public String PATIENT_CTL = APP_CONTEXT + "/ctl/PatientCtl";
	public String PATIENT_LIST_CTL = APP_CONTEXT + "/ctl/PatientListCtl";

	public String PATIENT_VIEW = PAGE_FOLDER + "/PatientView.jsp";
	public String PATIENT_LIST_VIEW = PAGE_FOLDER + "/PatientListView.jsp";

	public String CONTACT_CTL = APP_CONTEXT + "/ctl/ContactCtl";
	public String CONTACT_LIST_CTL = APP_CONTEXT + "/ctl/ContactListCtl";

	public String CONTACT_VIEW = PAGE_FOLDER + "/ContactView.jsp";
	public String CONTACT_LIST_VIEW = PAGE_FOLDER + "/ContactListView.jsp";

	public String LEAD_CTL = APP_CONTEXT + "/ctl/LeadCtl";
	public String LEAD_LIST_CTL = APP_CONTEXT + "/ctl/LeadListCtl";

	public String LEAD_VIEW = PAGE_FOLDER + "/LeadView.jsp";
	public String LEAD_LIST_VIEW = PAGE_FOLDER + "/LeadListView.jsp";

	public String WISHLIST_CTL = APP_CONTEXT + "/ctl/WishListCtl";
	public String WISHLIST_LIST_CTL = APP_CONTEXT + "/ctl/WishListListCtl";

	public String WISHLIST_VIEW = PAGE_FOLDER + "/WishListView.jsp";
	public String WISHLIST_LIST_VIEW = PAGE_FOLDER + "/WishListListView.jsp";

	public String PRESCRIPTION_CTL = APP_CONTEXT + "/ctl/PrescriptionCtl";
	public String PRESCRIPTION_LIST_CTL = APP_CONTEXT + "/ctl/PrescriptionListCtl";

	public String PRESCRIPTION_VIEW = PAGE_FOLDER + "/PrescriptionView.jsp";
	public String PRESCRIPTION_LIST_VIEW = PAGE_FOLDER + "/PrescriptionListView.jsp";

	public String DOCTOR_CTL = APP_CONTEXT + "/ctl/DoctorCtl";
	public String DOCTOR_LIST_CTL = APP_CONTEXT + "/ctl/DoctorListCtl";

	public String DOCTOR_VIEW = PAGE_FOLDER + "/DoctorView.jsp";
	public String DOCTOR_LIST_VIEW = PAGE_FOLDER + "/DoctorListView.jsp";

	public String SHOPPINGCARD_CTL = APP_CONTEXT + "/ctl/ShoppingCardCtl";
	public String SHOPPINGCARD_LIST_CTL = APP_CONTEXT + "/ctl/ShoppingCardListCtl";

	public String SHOPPINGCARD_VIEW = PAGE_FOLDER + "/ShoppingCardView.jsp";
	public String SHOPPINGCARD_LIST_VIEW = PAGE_FOLDER + "/ShoppingCardListView.jsp";

	public String PRODUCTDETAILS_CTL = APP_CONTEXT + "/ctl/ProductDetailsCtl";
	public String PRODUCTDETAILS_LIST_CTL = APP_CONTEXT + "/ctl/ProductDetailsListCtl";

	public String PRODUCTDETAILS_VIEW = PAGE_FOLDER + "/ProductDetailsView.jsp";
	public String PRODUCTDETAILS_LIST_VIEW = PAGE_FOLDER + "/ProductDetailsListView.jsp";

	public String ISSUE_CTL = APP_CONTEXT + "/ctl/IssueCtl";
	public String ISSUE_LIST_CTL = APP_CONTEXT + "/ctl/IssueListCtl";

	public String ISSUE_VIEW = PAGE_FOLDER + "/IssueView.jsp";
	public String ISSUE_LIST_VIEW = PAGE_FOLDER + "/IssueListView.jsp";

	public String PROJECT_CTL = APP_CONTEXT + "/ctl/ProjectCtl";
	public String PROJECT_LIST_CTL = APP_CONTEXT + "/ctl/ProjectListCtl";

	public String PROJECT_VIEW = PAGE_FOLDER + "/ProjectView.jsp";
	public String PROJECT_LIST_VIEW = PAGE_FOLDER + "/ProjectListView.jsp";

	public String SUPPLIER_CTL = APP_CONTEXT + "/ctl/SupplierCtl";
	public String SUPPLIER_LIST_CTL = APP_CONTEXT + "/ctl/SupplierListCtl";

	public String SUPPLIER_VIEW = PAGE_FOLDER + "/SupplierView.jsp";
	public String SUPPLIER_LIST_VIEW = PAGE_FOLDER + "/SupplierListView.jsp";

}