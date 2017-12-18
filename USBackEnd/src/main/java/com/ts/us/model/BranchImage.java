package com.ts.us.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

@Entity
@Component
public class BranchImage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int branchId;
	private String image;
	
	@ManyToOne
	@JoinColumn(name="branchId", updatable=false, insertable = false, nullable = false)
	private Branch branch;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	@Override
	public String toString() {
		return "BranchImage [id=" + id + ", branchId=" + branchId + ", image=" + image + "]";
	}
	
	
}
