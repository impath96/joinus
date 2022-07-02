package com.joinus.domain;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MemberVo {

	private Integer member_no;
	private String member_email;
	private String member_pass;
	private String member_name;
	private String member_location;
	private String member_tel;
	private String member_image;
	private Timestamp member_regdate;
	private Integer member_role;
	private String member_type;
	

}
