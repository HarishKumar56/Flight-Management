<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="<c:url value="/resources/bootstrap.css" />" rel="stylesheet">
<link href="<c:url value="/resources/style.css" />" rel="stylesheet">
<title>Insert title here</title>
</head>
<body>




<div class="container">
		<div class="card mx-auto vcenter ">
			<div class="card-header">
				<h2 class="card-title">Register</h2>
			</div>
			<div class="card-body">
				<form:form modelAttribute="user" method="post">
					<div class="form-group">
						<label for="exampleInputEmail1">User Name</label> 
						<form:input cssClass="form-control" path="userName"/>
					</div>
					<div class="form-group">
						<label for="exampleInputPassword1">Password</label> 
						
						 <form:input cssClass="form-control" path="password"/>
					</div>
					<button type="submit" class="btn btn-primary">Register</button>
				</form:form>
			</div>

		</div>
	</div>

</body>
</html>