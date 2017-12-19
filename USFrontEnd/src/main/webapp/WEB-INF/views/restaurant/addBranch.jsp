<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<div class="h2 text-center">Add Branch</div>
	<div class="col-md-4 col-md-offset-4">
		<div class="well">
			<form action="addBranch" method="post" enctype="multipart/form-data">
				<div class="form-group">
					<label for="location">Location:</label>
					<input type="text" class="form-control" id="location" name="location"
						placeholder="Branch Address" required>
				</div>
				<div class="form-group">
					<label for="city">City:</label>
					<input type="text" class="form-control" id="city" name="city" placeholder="Branch City"
						required>
				</div>
				<div class="form-group">
					<label for="state">State:</label>
					<input type="text" class="form-control" id="state" name="state" placeholder="Branch State"
						required>
				</div>
				<div class="form-group">
					<label for="country">Country:</label>
					<input type="text" class="form-control" id="country" name="country"
						placeholder="Branch Country" required>
				</div>
				<div class="form-group">
					<label for="postalCode">Postal Code:</label>
					<input type="number" class="form-control" id="postalCode" name="postalCode"
						placeholder="Branch Postal Code" required>
				</div>
				<div class="form-group">
					<label for="branchImages">Branch Images:</label>
					<input type="file" class="form-control" id="branchImages" name="branchImages"
						multiple="multiple" required>
				</div>
				<div class="text-right">
					<input type="submit" class="btn btn-success" value="Add Branch">
				</div>
			</form>
		</div>
	</div>
</body>
</html>