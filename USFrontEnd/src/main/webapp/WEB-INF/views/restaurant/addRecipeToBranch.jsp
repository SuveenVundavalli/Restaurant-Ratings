<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<div class="h2 text-center">Add Recipe To Branch</div>

	<div class="col-md-4 col-md-offset-4">
		<div class="well">
			<form action="addRecipeToBranch" method="post" enctype="multipart/form-data">
				<div class="form-group">
					<label for="sel1">Select Branch:</label> 
					<select class="form-control" id="sel1" name="branchId">
						<c:forEach items="${branchList }" var="branch">
							<option value="${branch.id }">${branch.location }, ${branch.city }</option>
						</c:forEach>
					</select>
					</div>
					<div class="form-group">
					<label for="sel1">Select Recipe:</label> 
					<select class="form-control" id="sel1" name="recipeId">
						<c:forEach items="${recipeList }" var="recipe">
							<option value="${recipe.id }">${recipe.name }</option>
						</c:forEach>
					</select>
				</div>
				<div class="form-group">
					<label for="price">Price:</label>
					<input type="number" class="form-control" id="price" name="price" placeholder="Recipe Price" required>
				</div>
				
				<div class="form-group">
					<label for="recipeImage">Recipe Image:</label>
					<input type="file" class="form-control" id="recipeImage" name="recipeImage" required>
				</div>
				
				<div class="text-right">
					<button type="submit" class="btn btn-success">Add Recipe</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>