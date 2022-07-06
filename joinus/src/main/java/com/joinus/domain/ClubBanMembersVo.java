package com.joinus.domain;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ClubBanMembersVo {
	
	private int club_ban_memberlist_no;
	private int club_no;
	private int member_no;
	private Timestamp club_member_regdate;
	private Timestamp club_member_bandate;
	

}
