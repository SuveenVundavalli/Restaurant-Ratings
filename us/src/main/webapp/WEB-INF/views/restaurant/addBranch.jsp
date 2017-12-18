<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h4>Branch</h4>
	<form action="branchSpring" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td><label>Location</label></td>
				<td>:</td>
				<td><input type="text" name="location"><br></td>
			</tr>
			<tr>
				<td><label>City</label></td>
				<td>:</td>
				<td><input type="text" name="city"></td>
			</tr>
			<tr>
				<td><label>State</label></td>
				<td>:</td>
				<td><input type="text" name="state"></td>
			</tr>
			<tr>
				<td><label>Country</label></td>
				<td>:</td>
				<td><input type="text" name="country"></td>
			</tr>
			<tr>
				<td><label>Postal Code</label></td>
				<td>:</td>
				<td><input type="text" name="postalCode"></td>
			</tr>
			<tr>
				<td><label>Branch Images</label></td>
				<td>:</td>
				<td><input type="file" name="branchImages" multiple="multiple"></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td><input type="submit" value="ADD BRANCH"></td>
			</tr>
		</table>
	</form>

</body>
</html>