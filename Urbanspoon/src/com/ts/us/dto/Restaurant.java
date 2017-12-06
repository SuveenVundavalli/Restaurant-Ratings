package com.ts.us.dto;

import java.util.List;

public class Restaurant {

	private long id;
	private String govtRegistrationtId;
	private String name;
	private String password;
	private String logo;

	private List<Branch> branchesList;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public List<Branch> getBranchesList() {
		return branchesList;
	}

	public void setBranchesList(List<Branch> branchesList) {
		this.branchesList = branchesList;
	}

	public String getGovtRegistrationtId() {
		return govtRegistrationtId;
	}

	public void setGovtRegistrationtId(String govtRegistrationtId) {
		this.govtRegistrationtId = govtRegistrationtId;
	}

	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", govtRegistrationtId=" + govtRegistrationtId + ", name=" + name
				+ ", password=" + password + ", logo=" + logo + ", branchesList=" + branchesList + "]";
	}

}
