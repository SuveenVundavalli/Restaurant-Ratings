<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to Urban Spoon</title>
<link href="<c:url value="/resources/css/bootstrap.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">

<script src="<c:url value="/resources/js/jquery-3.2.1.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.js" />"></script>
<script src="<c:url value="/resources/js/CustomJS.js" />"></script>
<script src="<c:url value="/resources/js/fontawesome-all.js" />"></script>

</head>
<body>
	<!-- Menu bar -->
	<div class="page">
		<div class="header">
			<jsp:include page="menu.jsp"></jsp:include>
		</div>

		<!-- Restaurants -->
		<c:if test="${isUserAtHomePage}">
			<jsp:include page="restaurants.jsp"></jsp:include>
		</c:if>

		<!-- Login -->
		<c:if test="${isUserClickedLogin}">
			<jsp:include page="login.jsp"></jsp:include>
		</c:if>
		
		<!-- User Registration -->
		<c:if test="${isUserClickerUserSignUp}">
			<jsp:include page="user/userSignup.jsp"></jsp:include>
		</c:if>
		
		<!-- Restaurant Registration -->
		<c:if test="${isUserClickedRestaurantSignUp}">
			<jsp:include page="restaurant/restaurantSignup.jsp"></jsp:include>
		</c:if>
		
		<div class="footer"></div>
	</div>
</body>
</html>