<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<h4>Branch</h4>
	<form action="branch" method="post" enctype="multipart/form-data">
		<input type="hidden" name="action" value="branch"> 
		<table>
			<tr>
				<td><label>Location</label></td>
				<td>:</td>
				<td><input type="text" name="location"><br> </td>
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
				<td><label>Postal Code</label> </td>
				<td>:</td>
				<td><input type="text" name="postal_code"></td>
			</tr>
			<tr>
				<td><label>Branch Images</label> </td>
				<td>:</td>
				<td><input type="file" name="branch_images" multiple="multiple"></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td><input type="submit" value="ADD BRANCH"></td>
			</tr>
		</table>
	</form>
	<br>
	<h4>Branch Using Spring</h4>
	<form action="branch_spring" method="post" enctype="multipart/form-data">
		<input type="hidden" name="action" value="branch"> 
		<table>
			<tr>
				<td><label>Location</label></td>
				<td>:</td>
				<td><input type="text" name="location"><br> </td>
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
				<td><label>Postal Code</label> </td>
				<td>:</td>
				<td><input type="text" name="postalCode"></td>
			</tr>
			<tr>
				<td><label>Branch Images</label> </td>
				<td>:</td>
				<td><input type="file" name="branch_images" multiple="multiple"></td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td><input type="submit" value="ADD BRANCH"></td>
			</tr>
		</table>
	</form>
	<br />
	<h4>Cuisine</h4>
	<form action="cuisine" method="post">
		<input type="hidden" name="action" value="cuisine"> 
		<table>
			<tr>
				<td><label>Name</label></td>
				<td>:</td>
				<td><input type="text" name="name"> </td>
			</tr>
			<tr>
				<td><label>Country</label></td>
				<td>:</td>
				<td><input type="text" name="country"><br> </td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td><input type="submit" value="ADD CUISINE"></td>
			</tr>
		</table>
	</form>
	<br>
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
				<td>	<label>Description</label></td>
				<td>:</td>
				<td><input type="text" name="description"></td>
			</tr>
			<tr>
				<td><label>Type</label></td>
				<td>:</td>
				<td>
					<input type="radio" name="recipe_type" value="Veg"> Veg 
					<input type="radio" name="recipe_type" value="Non-Veg"> Non-Veg
				</td>
			</tr>
			<tr>
				<td><label>Cuisine</label></td>
				<td>:</td>
				<td>
					<select name="cuisine_id">
						<c:forEach items="${cuisineList}" var="cuisine">
							<option value="${cuisine.id}">${cuisine.name}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td></td>
				<td></td>
				<td><input type="submit" value="ADD RECIPE"></td>
			</tr>
		</table>		
	</form>

	<br>
	<h4>ADD RECIPE TO BARNCH</h4>
	<form action="recipe_to_branch" method="post" enctype="multipart/form-data">
		<input type="hidden" name="action" value="recipe_to_branch">
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
	<br />
	<h4>ADD RECIPE TO BARNCH Spring</h4>
	<form action="recipe_to_branch_spring" method="post" enctype="multipart/form-data">
		<input type="hidden" name="action" value="recipe_to_branch">
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