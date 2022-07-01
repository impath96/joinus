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
	
	
	

}
