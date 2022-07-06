package com.joinus.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MembersVo {
	
	private Integer member_no;
	private String member_email;
	private String member_pass;
	private String member_name;
	private String member_tel;
	private String member_image;
	private Date member_regdate;
	private String member_authority;
	private String member_signup_type;
	private String member_status;
	
	
	
}
