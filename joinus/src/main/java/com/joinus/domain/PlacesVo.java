package com.joinus.domain;

public class PlacesVo {
	
	private int place_no;
	private String place_type;
	private String place_name;
	private String place_image;
	private String place_content;
	private String place_address;
	private String place_tel;
	public int getPlace_no() {
		return place_no;
	}
	public void setPlace_no(int place_no) {
		this.place_no = place_no;
	}
	public String getPlace_type() {
		return place_type;
	}
	public void setPlace_type(String place_type) {
		this.place_type = place_type;
	}
	public String getPlace_name() {
		return place_name;
	}
	public void setPlace_name(String place_name) {
		this.place_name = place_name;
	}
	public String getPlace_image() {
		return place_image;
	}
	public void setPlace_image(String place_image) {
		this.place_image = place_image;
	}
	public String getPlace_content() {
		return place_content;
	}
	public void setPlace_content(String place_content) {
		this.place_content = place_content;
	}
	public String getPlace_address() {
		return place_address;
	}
	public void setPlace_address(String place_address) {
		this.place_address = place_address;
	}
	public String getPlace_tel() {
		return place_tel;
	}
	public void setPlace_tel(String place_tel) {
		this.place_tel = place_tel;
	}
	
	
	@Override
	public String toString() {
		return "PlacesVo [place_no=" + place_no + ", place_type=" + place_type + ", place_name=" + place_name
				+ ", place_image=" + place_image + ", place_content=" + place_content + ", place_address="
				+ place_address + ", place_tel=" + place_tel + "]";
	}
	
	
	
}
