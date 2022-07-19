package com.ratnesh.rest.webservice.user.bean;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class MyAddress {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String district;
	private int pincode;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private MyUser myUser;
	
	protected MyAddress() {
		super();

	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MyUser getMyUser() {
		return myUser;
	}

	public void setMyUser(MyUser myUser) {
		this.myUser = myUser;
	}

	public MyAddress(String district, int pincode) {
		super();
		this.district = district;
		this.pincode = pincode;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	@Override
	public String toString() {
		return "MyAddress [id=" + id + ", district=" + district + ", pincode=" + pincode + "]";
	}

	
}
