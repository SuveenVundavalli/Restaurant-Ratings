<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to Urbanspoon</title>
<link href="<c:url value="/resources/css/bootstrap.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">

<script src="<c:url value="/resources/js/jquery-3.2.1.js" />"></script>
<script src="<c:url value="/resources/js/bootstrap.js" />"></script>
<script src="<c:url value="/resources/js/CustomJS.js" />"></script>
<script src="<c:url value="/resources/js/fontawesome-all.js" />"></script>
</head>
<body>
	<!-- Menu || Navbar -->
	<jsp:include page="menu.jsp"></jsp:include>

	<!-- Display Message -->
	<marquee>
		<p class="text-success">${successMessage}</p>
	</marquee>
	<marquee>
		<p class="text-danger">${errorMessage}</p>
	</marquee>


	<!-- Display restaurants when in homepage -->
	<c:if test="${isUserAtHomePage}">
		<jsp:include page="restaurants.jsp"></jsp:include>
	</c:if>

	<!-- Loggedin user homepage -->
	<c:if test="${loggedInAs == 'user'}">
		Loggedin as User
	</c:if>

	<!-- Loggedin restauant homepage -->
	<c:if test="${loggedInAs == 'restaurant'}">
		Loggedin as restaurant
	</c:if>
	
	<!-- Restaurant Operations -->
	<c:if test="${ isUserClickedAddBranch}">
		<jsp:include page="restaurant/addBranch.jsp"></jsp:include>
	</c:if>
	<c:if test="${ isUserClickedAddRecipe}">
		<jsp:include page="restaurant/addRecipe.jsp"></jsp:include>
	</c:if>
	<c:if test="${ isUserClickedAddCuisine}">
		<jsp:include page="restaurant/addCuisine.jsp"></jsp:include>
	</c:if>
	<c:if test="${ isUserClickedAddRecipeToBranch}">
		<jsp:include page="restaurant/addRecipeToBranch.jsp"></jsp:include>
	</c:if>
</body>
</html>