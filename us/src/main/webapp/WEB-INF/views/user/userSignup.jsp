<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body class="bodyCenter">
	
		<!-- Spring form starts here -->
		<div class="container vertical-center">
			<div class="col-md-6 col-sm-12">
				<div class="well">
				<h2 class="text-center">Sign Up</h2>
				<hr />
					<form:form action="user_registration_spring" modelAttribute="user" method="POST">
						<div class="form-group">
							<form:label path="name" cssClass="control-label">
								<spring:message text="Name" />
							</form:label>
							<div>
								<form:input path="name" required="true" cssClass="form-control" />
							</div>
						</div>
						<div class="form-group">
							<form:label path="email" cssClass="control-label">
								<spring:message text="Email/Username" />
							</form:label>
							<div >
								<form:input path="email" required="true" cssClass="form-control" />
							</div>
						</div>
						<div class="form-group">
							<form:label path="password" cssClass="control-label">
								<spring:message text="Password" />
							</form:label>
							<div >
								<form:password path="password" required="true" cssClass="form-control" />
							</div>
						</div>
						<div class="form-group">
							<form:label path="mobileNumber" cssClass="control-label">
								<spring:message text="Mobile" />
							</form:label>
							<div >
								<form:password path="mobileNumber" required="true" cssClass="form-control" />
							</div>
						</div>
						<div class="form-group">
							<form:label path="gender" cssClass="control-label">
								<spring:message text="Gender" />
							</form:label>
							<div >
								<div class="form-control">
									<form:radiobutton path="gender" value="Male" required="true" /> Male &nbsp;&nbsp;&nbsp;&nbsp;
									<form:radiobutton path="gender" value="Female" required="true" /> Female
								</div>
							</div>
						</div>
						<button type="submit" class="btn btn-success">Submit</button>
					</form:form>
				</div>
			</div>
		</div>

		<!-- Spring form Ends here -->

</body>
</html>