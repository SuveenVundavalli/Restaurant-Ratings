<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="user_registration_spring_form">
		<h3>User Registration Using Spring Form</h3>
		<form:form action="user_registration_spring" modelAttribute="user" method="POST">
			<table>

				<tr>
					<td>Name</td>
					<td>:</td>
					<td><form:input path="name" /></td>
				</tr>
				<tr>
					<td>Gender</td>
					<td>:</td>
					<td>Male<form:radiobutton path="gender" value="Male" /> female<form:radiobutton
							path="gender" value="Female" />
					</td>
				</tr>
				<tr>
					<td>Email/Username</td>
					<td>:</td>
					<td><form:input path="email" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td>:</td>
					<td><form:password path="password" /></td>
				</tr>
				<tr>
					<td>Mobile Number</td>
					<td>:</td>
					<td><form:input path="mobileNumber" /></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td><input type="submit" /></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>