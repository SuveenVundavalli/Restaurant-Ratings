<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body class="bodyCenter">
		
	<div class="container vertical-center">
		<div class="col-md-6">
			<div class="well">
				<form action="login" method="post">
					<div class="form-group">
						<label for="user_id">UserId:</label> 
						<input type="text" class="form-control" id="user_id" name="user_id" required>
					</div>
					<div class="form-group">
						<label for="password">Password:</label> 
						<input type="password" class="form-control" id="password" name="password" required>
					</div>
					<div class="form-group">
						<label class="radio-inline"><input type="radio" name="loginAs" value="user" required>User</label>
						<label class="radio-inline"><input type="radio" name="loginAs" value="restaurant" required>Restaurant</label>
					</div>
					<button type="submit" class="btn btn-success">Submit</button>
				</form>
			</div>
		</div>
	</div>

</body>
</html>