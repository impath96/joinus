package com.joinus.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class ClubMembersVo {
	
	private int club_member_no; 
	private int club_no; 
	private int member_no;
	private Date club_member_regdate;
	private int club_role_no;
	
}
