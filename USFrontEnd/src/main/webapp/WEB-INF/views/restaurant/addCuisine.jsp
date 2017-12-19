<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>

<div class="h2 text-center">Add Cuisine</div>

	<div class="col-md-4 col-md-offset-4">
		<div class="well">
			<form action="addCuisine" method="post">
				<div class="form-group">
					<label for="name">Name:</label>
					<input type="text" class="form-control" id="name" name="name" placeholder="Cuisine Name" required>
				</div>
				<div class="form-group">
					<label for="country">Country:</label>
					<input type="text" class="form-control" id="country" name="country" placeholder="Cuisine Country" required>
				</div>
				<div class="text-right">
					<button type="submit" class="btn btn-success">Add Recipe</button>
				</div>
			</form>
		</div>

	</div>


</body>
</html>