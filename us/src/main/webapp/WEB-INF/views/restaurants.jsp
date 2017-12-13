<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
		
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Document</title>
	</head>
<body>

	<div>
		<table class="table table-striped">
			<c:forEach items="${restaurantsList}" var="restaurant">
				<tr>
					<td>${restaurant.id}</td>
					<td>${restaurant.name}</td>
					<td>
					<img src="resources/images/restaurants/${restaurant.logo}" alt="${restaurant.logo}" class="img-rounded" width="50px" />
					</td>
					<td>
						<table border="1">
							<c:forEach items="${restaurant.branchesList}" var="branch">
								<tr>
									<td>${branch.id}</td>
									<td>${branch.location}</td>
									<td>${branch.city}</td>
									<td>${branch.state}</td>
									<td>${branch.country}</td>
									<td>${branch.postalCode}</td>
									<td>
										<table border="1">
											<c:forEach items="${branch.imagesList}" var="image">
												<tr>
													<td><img src="resources/images/branches/${image}" alt="${image}" class="img-rounded" width="50px" /></td>
												</tr>
											</c:forEach>
										</table>
									</td>
									<td>
										<table border="1">
											<c:forEach items="${branch.feedbackList}" var="feedback">
												<tr>
													<td>${feedback.id}</td>
													<td>${feedback.user.name}</td>
													<td>${feedback.feedbackType.description}</td>
													<td>${feedback.feedbackDate}</td>
													<td>${feedback.visitedDate}</td>
													<td>${feedback.comments}</td>
													<td>${feedback.ratings}</td>
													<td></td>
												</tr>
											</c:forEach>
										</table>
									</td>
									<td>
										<table border="1">
											<c:forEach items="${branch.cuisinesList}" var="cuisine">
												<tr>
													<td>${cuisine.id}</td>
													<td>${cuisine.name}</td>
													<td>${cuisine.country}</td>
													<td>
														<table class="table table-striped">
															<c:forEach items="${cuisine.recipesList}" var="recipe">
																<tr>
																	<td>${recipe.id}</td>
																	<td>${recipe.name}</td>
																	<td>${recipe.description}</td>
																	<td>${recipe.price}</td>
																	<td><img src="resources/images/recipes/${recipe.image}" alt="${recipe.image}" class="img-rounded" width="50px" /></td>
																	<td>
																		<table border="1">
																			<c:forEach items="${recipe.feedbackList}"
																				var="feedback">
																				<tr>
																					<td>${feedback.id}</td>
																					<td>${feedback.user.name}</td>
																					<td>${feedback.branch.location}</td>
																					<td>${feedback.feedbackDate}</td>
																					<td>${feedback.visitedDate}</td>
																					<td>${feedback.comments}</td>
																					<td>${feedback.ratings}</td>
																					<td></td>
																				</tr>
																			</c:forEach>
																		</table>
																	</td>
																</tr>
															</c:forEach>
														</table>
													</td>
												</tr>
											</c:forEach>
										</table>

									</td>
								</tr>
							</c:forEach>
						</table>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>