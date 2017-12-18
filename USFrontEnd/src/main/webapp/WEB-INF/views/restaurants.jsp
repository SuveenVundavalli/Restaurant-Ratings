<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Restaurants Home</title>
</head>
<body>

	<div class="container">
		<table class="table table-striped">
			<tr>
				<th>Image</th>
				<th>Name</th>
			</tr>
			<c:forEach items="${ restaurantsList }" var="restaurant">
				<tr>
					<td class="col-sm-4"><img src="resources/images/restaurants/${restaurant.logo}"
						alt="${restaurant.logo}" /></td>
					<td class="col-sm-8">
						<h2>
							<a href="showRestaurant/${restaurant.id}">${restaurant.name}</a>
						</h2>
					</td>
					<table class="table">
						<c:forEach items="${restaurant.branches }" var="branch">
							<tr>
								<td>${ branch.location}</td>
								<td>${ branch.city}</td>
								<td>${ branch.state}</td>
								<td>${ branch.country}</td>
								<td>${ branch.postalCode}</td>
								<td>
									<table class="table">
										<c:forEach items="${branch.branchImages}" var="branchImage">
											<tr>
												<img src="resources/images/branches/${branchImage.image}" alt="${branchImage.image}" />
											</tr>
										</c:forEach>
									</table>
								</td>
								<td>
									<table class="table">
										<c:forEach items="${branch.feedbacks}" var="feedback">
											<c:if test="${feedback.feedbackTypeId != null }">
												<tr>
													<td>${ feedback.userId }</td>
													<td>${ feedback.branchId }</td>
													<td>${ feedback.feedbackTypeId }</td>
													<td>${ feedback.rating }</td>
												</tr>
											</c:if>
											<tr>
												
											</tr>
										</c:forEach>
									</table>
									
									<table class="table">
										<c:forEach items="${branch.serves }" var="serve">
											<tr>
												<td>${serve.price}</td>
												<td>${serve.recipe.name }</td>
												<td>${serve.recipe.description }</td>
												<td>
												${serve.recipe}
													<%-- <c:choose>
														<c:when test="${serve.recipe.isVeg }">Vegetarian</c:when>
														<c:otherwise>Non-Veg</c:otherwise>
													</c:choose> --%>
												</td>
												<td>${serve.recipe.cuisine.name }</td>
												<td>${serve.recipe.cuisine.country }</td>
											</tr>
										</c:forEach>
									</table>
								</td>
							</tr>
						</c:forEach>
					</table>
				</tr>
			</c:forEach>
		</table>

	</div>
</body>
</html>