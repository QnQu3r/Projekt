package com.gruszka.datamodels;

public class Trip {
	private String type;
	private String address;
	private String price;
	private String date;
	
	public Trip(String t, String adr, String prc, String dat) {
		this.type = t;
		this.address = adr;
		this.price = prc;
		this.date = dat;
	}
	
	public String getType() {
		return this.type;
	}
	
	public String getPrice() {
		return this.price;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public String getDate() {
		return this.date;
	}
}
