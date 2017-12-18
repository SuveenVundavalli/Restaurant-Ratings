package com.ts.us.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.stereotype.Component;

@Entity
@Component
public class Branch {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String location;
	private String city;
	private String state;
	private String country;
	private int postalCode;
	private int restaurantId;
	
	@OneToMany(mappedBy="branch", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Serve> serves;

	@ManyToOne
	@JoinColumn(name="restaurantId", updatable=false, insertable = false, nullable = false)
	private Restaurant restaurant;
	
	@OneToMany(mappedBy = "branch", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<BranchImage> branchImages;
	
	@OneToMany(mappedBy = "branch", fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Feedback> feedbacks;

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

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public List<BranchImage> getBranchImages() {
		return branchImages;
	}

	public void setBranchImages(List<BranchImage> branchImages) {
		this.branchImages = branchImages;
	}

	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	public List<Serve> getServes() {
		return serves;
	}

	public void setServes(List<Serve> serves) {
		this.serves = serves;
	}

	@Override
	public String toString() {
		return "Branch [id=" + id + ", location=" + location + ", city=" + city + ", state=" + state + ", country="
				+ country + ", postalCode=" + postalCode + ", restaurantId=" + restaurantId + "]";
	}

	
	
}
