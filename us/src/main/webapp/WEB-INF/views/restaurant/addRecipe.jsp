<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<h4>Recipe</h4>
	<form action="recipe" method="post">
		<input type="hidden" name="action" value="recipe">
		<table>
			<tr>
				<td><label>Name</label></td>
				<td>:</td>
				<td><input type="text" name="recipe_name"></td>
			</tr>
			<tr>
				<td><label>Description</label></td>
				<td>:</td>
				<td><input type="text" name="description"></td>
			</tr>
			<tr>
				<td><label>Type</label></td>
				<td>:</td>
				<td><input type="radio" name="recipe_type" value="Veg"> Veg <input type="radio"
					name="recipe_type" value="Non-Veg"> Non-Veg</td>
			</tr>
			<tr>
				<td><label>Cuisine</label></td>
				<td>:</td>
				<td><select name="cuisine_id">
						<c:forEach items="${cuisineList}" var="cuisine">
							<option value="${cuisine.id}">${cuisine.name}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td><input type="submit" value="ADD RECIPE"></td>
			</tr>
		</table>
	</form>

</body>
</html>