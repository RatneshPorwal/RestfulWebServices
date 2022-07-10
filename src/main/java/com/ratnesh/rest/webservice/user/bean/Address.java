package com.ratnesh.rest.webservice.user.bean;

public class Address {
	
	private String district;
	private int pincode;
	public Address(String district, int pincode) {
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

}
