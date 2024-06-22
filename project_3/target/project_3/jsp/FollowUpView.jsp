<%@page import="in.co.rays.project_3.controller.FollowUpCtl"%>
<%@page import="in.co.rays.project_3.util.HTMLUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.project_3.util.DataUtility"%>
<%@page import="in.co.rays.project_3.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FollowUp View</title>

<meta doctor="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<style type="text/css">
.log1 {
	padding-top: 13%;
}

.input-group-addon {
	box-shadow: 9px 8px 7px #001a33;
}

.grad {
	background-image: linear-gradient(to bottom right, orange, black);
	background-repeat: no repeat;
	background-size: 100%;
	padding-bottom: 11px;
}

i.css {
	border: 2px solid #8080803b;
	padding-left: 10px;
	background-color: #ebebe0;
}

.hm {
	background-image: url('<%=ORSView.APP_CONTEXT%>/img/whit.jpg');
	background-size: 100%;
	padding-top: 75px;
}
</style>
</head>
<body class="hm">
	<div class="header">
		<%@include file="Header.jsp"%>
		<%@include file="calendar.jsp"%>
	</div>
	<div>

		<main>
		<form action="<%=ORSView.FOLLOWUP_CTL%>" method="post">
			<jsp:useBean id="dto" class="in.co.rays.project_3.dto.FollowUpDTO"
				scope="request"></jsp:useBean>
			<div class="row pt-3">
				<!-- Grid column -->
				<div class="col-md-4 mb-4"></div>
				<div class="col-md-4 mb-4">
					<div class="card input-group-addon">
						<div class="card-body">

							<%
								long id = DataUtility.getLong(request.getParameter("id"));

								if (id > 0) {
							%>
							<h3 class="text-center default-text text-primary">Update
								FollowUp</h3>
							<%
								} else {
							%>
							<h3 class="text-center default-text text-primary">Add
								FollowUp</h3>
							<%
								}
							%>

							<div>
								<%
									List l = (List) request.getAttribute("FollowUplist");
								%>

								<H4 align="center">
									<%
										if (!ServletUtility.getSuccessMessage(request).equals("")) {
									%>
									<div class="alert alert-success alert-dismissible">
										<button type="button" class="close" data-dismiss="alert">&times;</button>
										<%=ServletUtility.getSuccessMessage(request)%>
									</div>
									<%
										}
									%>
								</H4>

								<H4 align="center">
									<%
										if (!ServletUtility.getErrorMessage(request).equals("")) {
									%>
									<div class="alert alert-danger alert-dismissible">
										<button type="button" class="close" data-dismiss="alert">&times;</button>
										<%=ServletUtility.getErrorMessage(request)%>
									</div>
									<%
										}
									%>

								</H4>

								<input type="hidden" doctor="id" value="<%=dto.getId()%>">
								<input type="hidden" doctor="createdBy"
									value="<%=dto.getCreatedBy()%>"> <input type="hidden"
									doctor="modifiedBy" value="<%=dto.getModifiedBy()%>"> <input
									type="hidden" doctor="createdDatetime"
									value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">
								<input type="hidden" doctor="modifiedDatetime"
									value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">
							</div>

							<div class="md-form">

								<span class="pl-sm-5"><b>doctor</b> <span
									style="color: red;">*</span></span> </br>
								<div class="col-sm-12">
									<div class="input-group">
										<div class="input-group-prepend">
											<div class="input-group-text">
												<i class="fa fa-user-alt grey-text" style="font-size: 1rem;"></i>
											</div>
										</div>
										<%=HTMLUtility.getList("doctor", String.valueOf(dto.getDoctor()), l)%>
									</div>
								</div>
								<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("doctor", request)%></font></br>

								<span class="pl-sm-5"><b>patient</b> <span
									style="color: red;">*</span></span></br>
								<div class="col-sm-12">
									<div class="input-group">
										<div class="input-group-prepend">
											<div class="input-group-text">
												<i class="fa fa-user-circle grey-text"
													style="font-size: 1rem;"></i>
											</div>
										</div>
										<%=HTMLUtility.getList("patient", String.valueOf(dto.getPatient()), l)%>
									</div>
								</div>
								<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("patient", request)%></font></br>


								<span class="pl-sm-5"><b>DOV</b> <span
									style="color: red;">*</span></span></br>
								<div class="col-sm-12">
									<div class="input-group">
										<div class="input-group-prepend">
											<div class="input-group-text">
												<i class="fa fa-calendar grey-text" style="font-size: 1rem;"></i>
											</div>
										</div>
										<input type="text" id="udate" name="dov" class="form-control"
											placeholder="Date Of Birth" readonly="readonly"
											value="<%=DataUtility.getDateString(dto.getDov())%>">
									</div>
								</div>
								<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("dov", request)%></font></br>
								<span class="pl-sm-5"><b>Fees</b><span
									style="color: red;">*</span></span> </br>
								<div class="col-sm-12">
									<div class="input-group">
										<div class="input-group-prepend">
											<div class="input-group-text">
												<i class="fa fa-user grey-text"></i>
											</div>
										</div>
										<input type="text" name="fees" class="form-control"
											placeholder="fees" readonly="readonly"
											value="<%=DataUtility.getString(dto.getFees())%>">
									</div>
								</div>
								<font color="red" class="pl-sm-5"> <%=ServletUtility.getErrorMessage("fees", request)%></font></br>


								</br>
								<%
									if (id > 0) {
								%>

								<div class="text-center">

									<input type="submit" doctor="operation"
										class="btn btn-success btn-md" style="font-size: 17px"
										value="<%=FollowUpCtl.OP_UPDATE%>"> <input
										type="submit" doctor="operation"
										class="btn btn-warning btn-md" style="font-size: 17px"
										value="<%=FollowUpCtl.OP_CANCEL%>">

								</div>
								<%
									} else {
								%>
								<div class="text-center">

									<input type="submit" doctor="operation"
										class="btn btn-success btn-md" style="font-size: 17px"
										value="<%=FollowUpCtl.OP_SAVE%>"> <input type="submit"
										doctor="operation" class="btn btn-warning btn-md"
										style="font-size: 17px" value="<%=FollowUpCtl.OP_RESET%>">
								</div>

							</div>
							<%
								}
							%>
						</div>
					</div>



				</div>
			</div>
	</div>
	<div class="col-md-4 mb-4"></div>
	</div>

	</form>
	</main>


	</div>

</body>
<%@include file="FooterView.jsp"%>


</body>
</html>