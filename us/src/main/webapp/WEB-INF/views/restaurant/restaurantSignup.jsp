<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="restaurant_registration">
		<h3>Restaurant Register Using SPring File Upload</h3>
		<form name="restaurant_registration_form" method="post" action="restaurant_registration_spring" enctype="multipart/form-data">
			<input type="hidden" name="action" value="restaurant_registration">
			<table>
				<tr>
					<td><label>Name</label></td>
					<td></td>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<td><label>Government Registration ID</label></td>
					<td></td>
					<td><input type="text" name="govtRegistrationtId"></td>
				</tr>
				<tr>
					<td><label>Restaurant Logo</label></td>
					<td></td>
					<td><input type="file" name="registration_logo"></td>
				</tr>
				<tr>
					<td><label>Password</label></td>
					<td></td>
					<td><input type="password" name="password"></td>
				</tr>
				<tr>
					<td><label>Confirm Password</label></td>
					<td></td>
					<td><input type="password" name="confirm_password"></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</table>
			<input type="submit" value="register">
		</form>
	</div>
</body>
</html>