package com.ts.us.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.ts.us.dao.BranchDAO;
import com.ts.us.dao.RecipeDAO;
import com.ts.us.dto.Branch;
import com.ts.us.dto.Recipe;
import com.ts.us.dto.Restaurant;
import com.ts.us.exception.UrbanspoonException;
import com.ts.us.helper.UrbanspoonHelper;

/**
 * Servlet implementation class UrbanspoonController
 */
@WebServlet("/UrbanspoonController")
public class UrbanspoonController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UrbanspoonController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		try {
			if (action == null) {
				List<Restaurant> restaurantsList = UrbanspoonHelper.getRestaurants(true);
				request.setAttribute("restaurantsList", restaurantsList);
				request.getRequestDispatcher("home.jsp").forward(request, response);
			} else if (action.equals("branch_feedback")) {
				Branch branch = UrbanspoonHelper.getBranch(Integer.parseInt(request.getParameter("branch_id")), false);
				request.setAttribute("branch", branch);
				Restaurant restaurant = UrbanspoonHelper
						.getRestaurant(Integer.parseInt(request.getParameter("restaurant_id")), false);
				request.setAttribute("restaurant", restaurant);
				request.setAttribute("feedbackTypeList", UrbanspoonHelper.getFeedbackTypesList());
				request.getRequestDispatcher("userHome.jsp").forward(request, response);

			} else if (action.equals("recipe_feedback")) {
				Restaurant restaurant = UrbanspoonHelper
						.getRestaurant(Integer.parseInt(request.getParameter("restaurant_id")), false);
				request.setAttribute("restaurant", restaurant);
				Branch branch = UrbanspoonHelper.getBranch(Integer.parseInt(request.getParameter("branch_id")), false);
				request.setAttribute("branch", branch);
				Recipe recipe = UrbanspoonHelper.getRecipe(Integer.parseInt(request.getParameter("recipe_id")));
				request.setAttribute("recipe", recipe);
				request.getRequestDispatcher("userHome.jsp").forward(request, response);
			}
		} catch (UrbanspoonException e) {
			request.setAttribute("errorMessage", e.toString());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		try {
			if (isMultipart) {

				List<FileItem> fileItemsList = UrbanspoonHelper.getFileItems(request);
				String action = UrbanspoonHelper.getFormFeildValue(fileItemsList, "action");
				if (action != null) {
					if (action.equals("restaurant_registration")) {
						if (UrbanspoonHelper.addRestaurant(fileItemsList, request, response)) {
							response.sendRedirect("UrbanspoonController");
						}
					}
					if (action.equals("branch")) {
						if (UrbanspoonHelper.addBranch(fileItemsList, request, response)) {
							response.sendRedirect("UrbanspoonController");
						}
					}
					if (action.equals("recipe_to_branch")) {
						if (UrbanspoonHelper.addRecipeToBranch(fileItemsList, request, response)) {
							response.sendRedirect("UrbanspoonController");
						}
					}
				}
			} else {
				String action = request.getParameter("action");
				if (action.equals("user_registration")) {
					if (UrbanspoonHelper.addUser(request, response)) {
						response.sendRedirect("UrbanspoonController");
					}
				}
				if (action.equals("login")) {
					String loginAs = request.getParameter("loginAs");
					if (loginAs.equals("user")) {
						if (UrbanspoonHelper.loginAsUser(request, response)) {
							List<Restaurant> restaurantsList = UrbanspoonHelper.getRestaurants(true);
							request.setAttribute("restaurantsList", restaurantsList);
							request.getRequestDispatcher("userHome.jsp").forward(request, response);
						}
					} else if (loginAs.equals("restaurant")) {
						if (UrbanspoonHelper.loginAsRestaurantOwner(request, response)) {
							request.setAttribute("cuisineList", UrbanspoonHelper.getCuisine(false));
							request.setAttribute("branchList", UrbanspoonHelper.getBranches(request, true));
							request.setAttribute("recipeList", UrbanspoonHelper.getRecipes());
							request.getRequestDispatcher("restaurantHome.jsp").forward(request, response);
						}
					}
				} else if (action.equals("cuisine")) {
					if (UrbanspoonHelper.addCuisine(request, response)) {
						response.sendRedirect("UrbanspoonController");
					}

				} else if (action.equals("recipe")) {
					if (UrbanspoonHelper.addRecipe(request, response)) {
						response.sendRedirect("UrbanspoonController");
					}
				} else if (action.equals("branch_feedback")) {
					if (UrbanspoonHelper.addBranchFeedback(request, response)) {
						response.sendRedirect("UrbanspoonController");
					}

				} else if (action.equals("recipe_feedback")) {
					if (UrbanspoonHelper.addRecipeFeedback(request, response)) {

						response.sendRedirect("UrbanspoonController");
					}
				}
			}

		} catch (UrbanspoonException e) {
			request.setAttribute("errorMessage", e.toString());
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}

	}
}
