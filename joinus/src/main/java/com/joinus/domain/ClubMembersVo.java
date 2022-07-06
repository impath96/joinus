package com.joinus.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ClubMembersVo {
	
	private int club_memberlist_no;
	private int club_no;
	private int member_no;
	private int club_role_no;
	private Timestamp club_member_regdate;
	

}
