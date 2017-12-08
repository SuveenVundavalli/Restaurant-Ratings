package com.ts.us.dto;

import java.util.List;

public class Branch {

	private int id;
	private String location;
	private String city;
	private String state;
	private String country;
	private int postalCode;
	private List<String> imagesList;
	private List<Cuisine> cuisinesList;
	private List<Feedback> feedbackList;

	
	public List<Feedback> getFeedbackList() {
		return feedbackList;
	}

	public void setFeedbackList(List<Feedback> feedbackList) {
		this.feedbackList = feedbackList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public List<Cuisine> getCuisinesList() {
		return cuisinesList;
	}

	public void setCuisinesList(List<Cuisine> cuisinesList) {
		this.cuisinesList = cuisinesList;
	}

	public List<String> getImagesList() {
		return imagesList;
	}

	public void setImagesList(List<String> imagesList) {
		this.imagesList = imagesList;
	}

	@Override
	public String toString() {
		return "Branch [id=" + id + ", location=" + location + ", city=" + city + ", state=" + state + ", country="
				+ country + ", postalCode=" + postalCode + ", imagesList=" + imagesList + ", cuisinesList="
				+ cuisinesList + "]";
	}
}
