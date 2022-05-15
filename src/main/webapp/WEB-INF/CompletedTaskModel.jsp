<%@page import="com.CompletedTaskmodel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/CompletedTaskmodel.js"></script>
</head>
<body>
<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Completed Task </h1>

				<form id="formCom" name="formCom">
					Task ID: <input id="tid" name="Tid" type="text" class="form-control form-control-sm"> <br>
					Completed Date: <input id="compdate" name="section" type="text" class="form-control form-control-sm"> <br> 
					Expenditure: <input id="exp" name="cus" type="text" class="form-control form-control-sm"> <br> 
					<input type="hidden" id="comid" name="comid" value="">
					 
						 <input id="btnSave" name="btnSave" type="button" value="Submit" class="btn btn-primary"> 
				</form><br>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divGrid">
					<%
						String Task="1";
					CompletedTaskmodel comp=new CompletedTaskmodel();
						out.print(comp.readtask(Task));
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>