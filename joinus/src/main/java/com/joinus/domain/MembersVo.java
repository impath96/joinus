package com.joinus.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class MembersVo {
	
	private int member_no;
	private String member_email;
	private String member_pass;
	private String member_name;
	private String member_tel;
	private String member_image;
	private Date member_regdate;
	private int member_role;
	
	
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	public String getMember_email() {
		return member_email;
	}
	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}
	public String getMember_pass() {
		return member_pass;
	}
	public void setMember_pass(String member_pass) {
		this.member_pass = member_pass;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public String getMember_tel() {
		return member_tel;
	}
	public void setMember_tel(String member_tel) {
		this.member_tel = member_tel;
	}
	public String getMember_image() {
		return member_image;
	}
	public void setMember_image(String member_image) {
		this.member_image = member_image;
	}
	public Date getMember_regdate() {
		return member_regdate;
	}
	public void setMember_regdate(Date member_regdate) {
		this.member_regdate = member_regdate;
	}
	public int getMember_role() {
		return member_role;
	}
	public void setMember_role(int member_role) {
		this.member_role = member_role;
	}
	
	
	@Override
	public String toString() {
		return "MembersVo [member_no=" + member_no + ", member_email=" + member_email + ", member_pass=" + member_pass
				+ ", member_name=" + member_name + ", member_tel=" + member_tel + ", member_image=" + member_image
				+ ", member_regdate=" + member_regdate + ", member_role=" + member_role + "]";
	}
	
	
	
	
	

}
