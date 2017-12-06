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
	<form action="UrbanspoonController" method="post" enctype="multipart/form-data">
		<input type="hidden" name="action" value="branch"> <label>Location</label>
		<input type="text" name="location"><br> <label>City</label>
		<input type="text" name="city"> <br> <label>State</label>
		State<input type="text" name="state"><br> <label>Country</label>
		Country<input type="text" name="country"><br> <label>Postal
			Code</label> PostalCode<input type="text" name="postal_code"><br>

		<label>Branch Images</label> <input type="file" name="branch_images" multiple="multiple"><br> 
		<input type="submit" value="ADD BRANCH">

	</form>
	<br>
	<h4>Cuisine</h4>
	<form action="UrbanspoonController" method="post">
		<input type="hidden" name="action" value="cuisine"> <label>Name</label>
		<input type="text" name="name"><br> <label>Country</label>
		<input type="text" name="country"><br> <input
			type="submit" value="ADD CUISINE">

	</form>
	<br>
	<h4>Recipe</h4>
	<form action="UrbanspoonController" method="post">

		<input type="hidden" name="action" value="recipe"> <label>Name</label>
		<input type="text" name="name"><br> <label>Description</label>
		<input type="text" name="description"><br> <label>Type</label>
		<input type="radio" name="type" value="Veg"> Veg <input
			type="radio" name="type" value="Non-Veg"> Non-Veg<br> <label>Cuisine</label>
		<select name="cuisine_id">
			<c:forEach items="${cuisineList}" var="cuisine">
				<option value="${cuisine.id}">${cuisine.name}</option>
			</c:forEach>
		</select><br> <input type="submit" value="ADD RECIPE">
	</form>

	<br>
	<h4>ADD RECIPE TO BARNCH</h4>
	<form action="UrbanspoonController" method="post"
		enctype="multipart/form-data">

		<input type="hidden" name="action" value="recipe_to_branch">
		<label>Branch</label> <select name="branch_id">
			<c:forEach items="${branchList}" var="branch">
				<option value="${branch.id}">${branch.location}</option>
			</c:forEach>
		</select><br> <label>Recipe</label> <select name="recipei_id">
			<c:forEach items="${branchList}" var="branch">
				<c:forEach items="${branch.cuisinesList}" var="cuisine">
					<c:forEach items="${cuisine.recipesList}" var="recipe">
						<option value="${recipe.id}">${recipe.name}</option>
					</c:forEach>
				</c:forEach>
			</c:forEach>
		</select><br> <label>Price</label> <input type="number" name="price"><br>
		<label>ImagePath</label> <input type="file"><br> <input
			type="submit" value="ADD RECIPE TO BARNCH">

	</form>


</body>
</html>