package com.ts.us.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Serve {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private double price;
	private String image;
	private int branchId, recipeId;	

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="branchId", updatable=false, insertable = false, nullable = false)
	private Branch branch;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="recipeId", updatable=false, insertable = false, nullable = false)
	private Recipe recipe;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public int getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	@Override
	public String toString() {
		return "Serve [id=" + id + ", price=" + price + ", image=" + image + ", branchId=" + branchId + ", recipeId="
				+ recipeId + "]";
	}

	
}
