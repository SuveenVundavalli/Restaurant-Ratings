<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h4>ADD RECIPE TO BARNCH Spring</h4>
	<form action="recipe_to_branch_spring" method="post" enctype="multipart/form-data">
		<table>
			<tr>
				<td><label>Branch</label></td>
				<td>:</td>
				<td>
					<select name="branch_id">
						<c:forEach items="${branchList}" var="branch">
							<option value="${branch.id}">${branch.location}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td><label>Recipe</label></td>
				<td>:</td>
				<td>
					<select name="recipe_id">
						<c:forEach items="${ recipeList}" var="recipe">
							<option value="${recipe.id}">${recipe.name}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td><label>Price</label></td>
				<td>:</td>
				<td><input type="number" name="price"></td>
			</tr>
			<tr>
				<td><label>ImagePath</label> </td>
				<td>:</td>
				<td><input type="file" name="recipe_image"></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td><input type="submit" value="ADD RECIPE TO BARNCH"></td>
			</tr>
		</table>
	</form>
</body>
</html>