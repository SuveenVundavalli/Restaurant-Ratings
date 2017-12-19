<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="home">UrbanSpoon</a>
		</div>
		<ul class="nav navbar-nav">
			<li class="active"><a href="#">Home</a></li>
			<c:if test="${loggedInAs == 'restaurant'}">
				<li><a href="addBranch">Add Branch</a></li>
				<li><a href="addRecipe">Add Recipe</a></li>
				<li><a href="addCuisine">Add Cuisine</a></li>
				<li><a href="addRecipeToBranch">Add Recipe To Branch</a></li>
			</c:if>
		</ul>
		<form class="navbar-form navbar-left" action="/action_page.php">
			<div class="form-group">
				<input type="text" class="form-control" placeholder="Search">
			</div>
			<button type="submit" class="btn btn-default">Submit</button>
		</form>
		<c:if test="${empty loggedInUser}">
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<span class="glyphicon glyphicon-user"></span> Sign Up <b class="caret"></b>
					</a>
					<ul class="dropdown-menu">
						<li><a href="#" data-toggle="modal" data-target="#user-signup-modal">
								<span class="glyphicon glyphicon-log-in"></span> As User
							</a></li>
						<li class="divider"></li>
						<li><a href="#" data-toggle="modal" data-target="#restaurant-signup-modal">
								<span class="glyphicon glyphicon-log-in"></span> As Restaurant
							</a></li>

						<li><a href="getSignUpUser">
								<i class="far fa-user"></i> As User
							</a></li>
						<li class="divider"></li>
						<li><a href="getSignUpRestaurant">
								<i class="fas fa-utensils"></i> As Restaurant
							</a></li>
					</ul></li>
				<li><a href="#" data-toggle="modal" data-target="#login-modal">
						<span class="glyphicon glyphicon-log-in"></span> Login
					</a></li>
				<li><a href="getLogin">
						<span class="glyphicon glyphicon-log-in"></span> Login
					</a></li>
			</ul>
		</c:if>
		<c:if test="${not empty loggedInUser }">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="signOut">
						<span class="glyphicon glyphicon-log-out"></span> SignOut
					</a></li>
			</ul>
		</c:if>
	</div>
	</nav>
	<div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">Close</span>
					</button>
					<h3 class="modal-title" id="lineModalLabel">Welcome back!</h3>
				</div>
				<div class="modal-body">
					<form action="login" method="post">
						<div class="form-group">
							<label for="userId">Username:</label>
							<input type="text" class="form-control" id="userId" name="userId" placeholder="Username" required>
						</div>
						<div class="form-group">
							<label for="password">Password:</label>
							<input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
						</div>

						<div class="form-group">
							<label>Login As:</label> <label class="radio-inline"> <input type="radio" name="loginAs" value="user" required> User
							</label> <label class="radio-inline"> <input type="radio" name="loginAs" value="restaurant" required> Restaurant
							</label>
						</div>
						<div class="text-right">
							<button type="submit" class="btn btn-success">Login</button>
						</div>
					</form>
				</div>
			</div>

		</div>
	</div>
	<div class="modal fade" id="user-signup-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">Close</span>
					</button>
					<h3 class="modal-title" id="lineModalLabel">User Signup</h3>
				</div>
				<div class="modal-body">
					<form action="userRegistration" method="post">
						<div class="form-group">
							<label for="firstName">First Name:</label>
							<input type="text" class="form-control" id="firstName" name="firstName" placeholder="First Name" required>
						</div>
						<div class="form-group">
							<label for="lastName">Last Name:</label>
							<input type="text" class="form-control" id="lastName" name="lastName" placeholder="Last Name" required>
						</div>
						<div class="form-group">
							<label for="email">Email:</label>
							<input type="email" class="form-control" id="email" name="email" placeholder="Email" required>
						</div>
						<div class="form-group">
							<label for="password">Password:</label>
							<input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
						</div>
						<div class="form-group">
							<label for="mobileNumber">Mobile Number:</label>
							<input type="mobileNumber" class="form-control" id="mobileNumber" name="mobileNumber" placeholder="Mobile Number" required>
						</div>
						<div class="form-group">
							<label>Gender:</label> <label class="radio-inline"><input type="radio" name="gender" value="M" required>Male</label> <label
								class="radio-inline"><input type="radio" name="gender" value="F" required>Female</label>
						</div>
						<div class="text-right">
							<button type="submit" class="btn btn-success">Submit</button>
						</div>
					</form>
				</div>
			</div>

		</div>
	</div>

	<div class="modal fade" id="restaurant-signup-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
		style="display: none;">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">×</span><span class="sr-only">Close</span>
					</button>
					<h3 class="modal-title" id="lineModalLabel">Restaurant SignUp</h3>
				</div>
				<div class="modal-body">
					<form name="restaurant_registration_form" method="post" action="restaurantRegistration" enctype="multipart/form-data">
						<div class="form-group">
							<label for="name">Name:</label>
							<input type="text" class="form-control" id="name" name="name" placeholder="Restaurant Name" required>
						</div>
						<div class="form-group">
							<label for="govtRegistrationId">Government Registration ID:</label>
							<input type="text" class="form-control" id="govtRegistrationId" name="govtRegistrationId" placeholder="Governament Registration Id" required>
						</div>
						<div class="form-group">
							<label for="password">Password:</label>
							<input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
						</div>
						<div class="form-group">
							<label for="confirmPassword">Confirm Password:</label>
							<input type="text" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Confirm Password" required>
						</div>
						<div class="form-group">
							<label for="registrationLogo">Logo:</label>
							<input type="file" class="form-control" id="registrationLogo" name="registrationLogo" required>
						</div>
						<div class="text-right">
							<input type="submit" class="btn btn-success" value="register">
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>