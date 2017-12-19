<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
			<li><a href="#">Page 1</a></li>
			<li><a href="#">Page 2</a></li>
		</ul>
		<form class="navbar-form navbar-left" action="/action_page.php">
			<div class="form-group">
				<input type="text" class="form-control" placeholder="Search">
			</div>
			<button type="submit" class="btn btn-default">Submit</button>
		</form>
		<ul class="nav navbar-nav navbar-right">
			<li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span> Sign Up
					<b class="caret"></b>
			</a>
				<ul class="dropdown-menu">
			<li><a href="#" data-toggle="modal" data-target="#user-signup-modal"><span class="glyphicon glyphicon-log-in"></span> As User</a></li>
				<li class="divider"></li>
				<li><a href="#" data-toggle="modal" data-target="#restaurant-signup-modal"><span class="glyphicon glyphicon-log-in"></span> As Restaurant</a></li> 
				
					<li><a href="getSignUpUser"><i class="far fa-user"></i> As User</a></li>
					<li class="divider"></li>
					<li><a href="getSignUpRestaurant"><i class="fas fa-utensils"></i> As Restaurant</a></li>
				</ul></li>
			<li><a href="#" data-toggle="modal" data-target="#login-modal"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
			<li><a href="getLogin" ><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
		</ul>
	</div>
	</nav>

		<div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
			<div class="modal-dialog">
				<div class="loginmodal-container">
					<h1>Login to Your Account</h1>
					<br>
					<form>
						<input type="text" name="user" placeholder="Username"> <input type="password"
							name="pass" placeholder="Password"> <input type="submit" name="login"
							class="login loginmodal-submit" value="Login">
					</form>
				</div>
			</div>
		</div>
		<div class="modal fade" id="user-signup-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
			<div class="modal-dialog">
				<div class="loginmodal-container">
					<h1>Signup as User</h1>
					<br>
					<form>
						<input type="text" name="user" placeholder="Username"> 
						<input type="password" name="pass" placeholder="Password"> 
						<input type="submit" name="login" class="login loginmodal-submit" value="Login">
					</form>
				</div>
			</div>
		</div>
		<div class="modal fade" id="restaurant-signup-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
			<div class="modal-dialog">
				<div class="loginmodal-container">
					<h1>Signup as Restaurant</h1>
					<br>
					<form>
						<input type="text" name="user" placeholder="Username"> 
						<input type="password" name="pass" placeholder="Password"> 
						<input type="submit" name="login" class="login loginmodal-submit" value="Login">
					</form>
				</div>
			</div>
		</div>
	
</body>
</html>