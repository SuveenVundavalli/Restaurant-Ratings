<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="page">
		<div class="header"></div>
		<div>
			<div id="restaurants">
				<table border="1">
					<c:forEach items="${restaurantsList}" var="restaurant">
						<tr>
							<td>${restaurant.id}</td>
							<td>${restaurant.name}</td>
							<td>${restaurant.logo}</td>
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
											<td><a
												href="UrbanspoonController?action=branch_feedback&restaurant_id=${restaurant.id}&branch_id=${branch.id}">Add
													FeedBack</a></td>
											<td>
												<table border="1">
													<c:forEach items="${branch.imagesList}" var="image">
														<tr>
															<td>${image}</td>
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
																<table>
																	<c:forEach items="${cuisine.recipesList}" var="recipe">
																		<tr>
																			<td>${recipe.id}</td>
																			<td>${recipe.name}</td>
																			<td>${recipe.description}</td>
																			<td>${recipe.price}</td>
																			<td><a
																				href="UrbanspoonController?action=recipe_feedback&recipe_id=${recipe.id}&branch_id=${branch.id}&restaurant_id=${restaurant.id}">Add
																					FeedBack</a></td>
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
			<div id="branch_feedback">
				<h3>Branch Feedback</h3>
				<form name="branch_feedback_form" method="post"
					action="UrbanspoonController">
					<input type="hidden" name="action" value="branch_feedback"><br>
					Restaurant:
					<h3>${restaurant.name}</h3>
					<br> Branch:
					<h3>${branch.location}</h3>
					<input type="hidden" name="branch_id" value="${branch.id}">
					<br> <label>Comments</label>
					<textarea rows="5" cols="5" name="comments"></textarea>
					<br><label>rating</label> <select name="rating">
						<option value=1>1</option>
						<option value=2>2</option>
						<option value=3>3</option>
						<option value=4>4</option>
						<option value=5>5</option>
					</select> <br>
					<label>Visited Date</label><input type="date" name="visited_Date"><br>
					<label>FeedbackType</label> <select name="feedback_type_id">
						<c:forEach items="${feedbackTypeList}" var="feedbackType">
							<option value="${feedbackType.id}">${feedbackType.description}</option>
						</c:forEach>
					</select><br> <input type="submit" value="submit">
				</form>
			</div>


			<div id="recipe_feedback">
				<h3>Recipe Feedback</h3>
				<form name="recipe_feedback_form" method="post"
					action="UrbanspoonController">
					<input type="hidden" name="action" value="recipe_feedback"><br>
					Restaurant:
					<h3>${restaurant.name}</h3>
					<input type="hidden" name="branch_id" value="${branch.id}">
					<input type="hidden" name="recipe_id" value="${recipe.id}">
					<br> Branch:
					<h3>${branch.location}</h3>
					<br> Recipe:
					<h3>${recipe.name}</h3>
					<label>Comments</label>
					<textarea rows="5" cols="5" name="comments"></textarea>
					<br> <label>rating</label> <select name="rating">
						<option value=1>1</option>
						<option value=2>2</option>
						<option value=3>3</option>
						<option value=4>4</option>
						<option value=5>5</option>
					</select> <br>
					<label>Visited Date</label><input type="date" name="visited_Date"><br>


					<input type="submit" value="submit">
				</form>
			</div>
		</div>
	</div>
</body>
</html>