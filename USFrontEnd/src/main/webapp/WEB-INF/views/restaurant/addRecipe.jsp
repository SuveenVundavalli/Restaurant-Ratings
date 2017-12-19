<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>

	<div class="h2 text-center">Add Recipe</div>

	<div class="col-md-4 col-md-offset-4">
		<div class="well">
			<form action="addRecipe" method="post">
				<div class="form-group">
					<label for="name">Name:</label>
					<input type="text" class="form-control" id="name" name="name" placeholder="Recipe Name" required>
				</div>
				<div class="form-group">
					<label for="description">Description:</label>
					<input type="text" class="form-control" id="description" name="description" placeholder="Recipe Description" required>
				</div>
				<div class="form-group">	
					<label>Is Vegetarian:</label> 
					<label class="radio-inline"> 
						<input type="radio" name="isVeg" value="true" required> Yes
					</label> 
					<label class="radio-inline"> 
							<input type="radio" name="isVeg" value="false" required> No
					</label>
				</div>
				<div class="form-group">
					<label for="sel1">Select list:</label> 
					<select class="form-control" id="sel1" name="cuisineId">
						<c:forEach items="${cuisinesList }" var="cuisine">
							<option value="${cuisine.id }">${cuisine.name },${cuisine.country }</option>
						</c:forEach>
					</select>
				</div>
				<div class="text-right">
					<button type="submit" class="btn btn-success">Add Recipe</button>
				</div>
			</form>
		</div>

	</div>
</body>
</html>