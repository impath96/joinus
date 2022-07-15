package com.joinus.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class ClubMeetingsVo {
	
	private int club_meeting_no; 
	private int club_no; 
	private String club_meeting_title; 
	private int club_meeting_capacity; 
	private Date club_meeting_date; 
	private String club_meeting_location; 
	private int club_meeting_dues;
	private String club_meeting_address;

}
