<%@page import="in.co.rays.project_3.util.HTMLUtility"%>
<%@page import="in.co.rays.project_3.util.DataUtility"%>
<%@page import="in.co.rays.project_3.dto.PatientDTO"%>
<%@page import="in.co.rays.project_3.controller.PatientListCtl"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.project_3.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List view</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=ORSView.APP_CONTEXT%>/js/CheckBox11.js"></script>
<style>
.p1 {
	padding: 8px;
}

.hm {
	background-image:
		url('<%=ORSView.APP_CONTEXT%>/img/background-g1d93e894b_1920.jpg');
	background-size: 100%;
	padding-top: 60px;
}
</style>
</head>
<body class="hm">
	<div>
		<%@include file="Header.jsp"%>
		<%@include file="calendar.jsp"%>
	</div>
	<div>
		<form action="<%=ORSView.PATIENT_LIST_CTL%>" method="post">





			<jsp:useBean id="dto" class="in.co.rays.project_3.dto.PatientDTO"
				scope="request"></jsp:useBean>
			<%
				List list1 = (List) request.getAttribute("Patientlist");
			%>


			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;
				int nextPageSize = DataUtility.getInt(request.getAttribute("nextListSize").toString());
				List list = ServletUtility.getList(request);
				Iterator<PatientDTO> it = list.iterator();
				if (list.size() != 0) {
			%>
			<center>
				<h1 class="text-primary font-weight-bold pt-3">Patient List</h1>
			</center>

			</br>
			<div class="row">
				<div class="col-md-4"></div>

				<%
					if (!ServletUtility.getSuccessMessage(request).equals("")) {
				%>

				<div class="col-md-4 alert alert-success alert-dismissible"
					style="background-color: #80ff80">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<h4>
						<font color="#008000"><%=ServletUtility.getSuccessMessage(request)%></font>
					</h4>
				</div>
				<%
					}
				%>

				<div class="col-md-4"></div>
			</div>
			<div class="row">
				<div class="col-md-4"></div>

				<%
					if (!ServletUtility.getErrorMessage(request).equals("")) {
				%>
				<div class=" col-md-4 alert alert-danger alert-dismissible">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<h4>
						<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
					</h4>
				</div>
				<%
					}
				%>
				<div class="col-md-4"></div>
			</div>
			<div class="row">

				<div class="col-sm-2">
					<input class="form-control" type="text" name="decease"
						placeholder="Enter decease" class="p1"
						value="<%=ServletUtility.getParameter("decease", request)%>">
				</div>
				<div class="col-sm-2">
					<input class="form-control" type="text" name="name"
						placeholder="Enter name" class="p1"
						value="<%=ServletUtility.getParameter("name", request)%>">
				</div>

				<div class="col-sm-2">
					<input class="form-control" type="text" name="mobile"
						placeholder="Enter mobile" class="p1"
						value="<%=ServletUtility.getParameter("mobile", request)%>">
				</div>
				<div class="col-sm-2">
					<input type="text" id="udate" name="dov" class="form-control"
						placeholder="Date Of Birth" readonly="readonly"
						value="<%=ServletUtility.getParameter("dov", request)%>">
				</div>

				<div class="col-sm-2">
					<input type="submit" class="btn btn-primary btn-md"
						style="font-size: 17px" name="operation"
						value="<%=PatientListCtl.OP_SEARCH%>">&emsp; <input
						type="submit" class="btn btn-dark btn-md" style="font-size: 17px"
						name="operation" value="<%=PatientListCtl.OP_RESET%>">
				</div>


				<div class="col-sm-2"></div>

			</div>


			</br>
			<div style="margin-bottom: 20px;" class="table-responsive">
				<table class="table  table-info table-bordered table-hover">
					<thead>
						<tr style="background-color: #8C8C8C;">

							<th width="10%"><input type="checkbox" id="select_all"
								name="Select" class="text"> Select All</th>
							<th class="text">S.NO</th>

							<th class="text">Name</th>
							<th class="text">mobile</th>
							<th class="text">dov</th>
							<th class="text">decease</th>

							<th class="text">Edit</th>
						</tr>
					</thead>
					<%
						while (it.hasNext()) {
								dto = it.next();
					%>

					<tbody>
						<tr>
							<td align="center"><input type="checkbox" class="checkbox"
								name="ids" value="<%=dto.getId()%>"></td>
							<td><%=index++%></td>

							<td><%=dto.getName()%></td>
							<td><%=dto.getMobile()%></td>
							<td><%=DataUtility.getDateString(dto.getDov())%></td>
							<td><%=dto.getDecease()%></td>

							<td align="center"><a href="PatientCtl?id=<%=dto.getId()%>">Edit</a></td>
						</tr>
					</tbody>
					<%
						}
					%>
				</table>
			</div>
			<table width="100%">
				<tr>
					<td><input type="submit" name="operation"
						class="btn btn-secondary btn-md" style="font-size: 17px"
						value="<%=PatientListCtl.OP_PREVIOUS%>"
						<%=pageNo > 1 ? "" : "disabled"%>></td>
					<td><input type="submit" name="operation"
						class="btn btn-primary btn-md" style="font-size: 17px"
						value="<%=PatientListCtl.OP_NEW%>"></td>
					<td><input type="submit" name="operation"
						class="btn btn-danger btn-md" style="font-size: 17px"
						value="<%=PatientListCtl.OP_DELETE%>"></td>

					<td align="right"><input type="submit" name="operation"
						class="btn btn-secondary btn-md" style="font-size: 17px"
						style="padding: 5px;" value="<%=PatientListCtl.OP_NEXT%>"
						<%=(nextPageSize != 0) ? "" : "disabled"%>></td>
				</tr>
				<tr></tr>
			</table>

			<%
				}
				if (list.size() == 0) {
					System.out.println("user list view list.size==0");
			%>
			<center>
				<h1 class="text-primary font-weight-bold pt-3">Patient List</h1>
			</center>
			</br>
			<div class="row">
				<div class="col-md-4"></div>


				<%
					if (!ServletUtility.getSuccessMessage(request).equals("")) {
				%>

				<div class="col-md-4 alert alert-success alert-dismissible"
					style="background-color: #80ff80">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<h4>
						<font color="#008000"><%=ServletUtility.getSuccessMessage(request)%></font>
					</h4>
				</div>
				<%
					}
				%>




				<%
					if (!ServletUtility.getErrorMessage(request).equals("")) {
				%>
				<div class=" col-md-4 alert alert-danger alert-dismissible">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<h4>
						<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
					</h4>
				</div>
				<%
					}
				%>
				<div class="col-md-4"></div>
			</div>
			</br>
			<div style="padding-left: 48%;">
				<input type="submit" name="operation" class="btn btn-primary btn-md"
					style="font-size: 17px" value="<%=PatientListCtl.OP_BACK%>">
			</div>
			<%
				}
			%>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">


		</form>

	</div>
	</br>
	</br>
</body>
<%@include file="FooterView.jsp"%>

</html>