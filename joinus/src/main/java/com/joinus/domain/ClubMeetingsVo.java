package com.joinus.domain;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class ClubMeetingsVo {

	private int club_meeting_no;
	private int club_no;
	private int club_meeting_capacity;
	private Date club_meeting_date;
	private String club_meeting_location;
	private int club_meeting_dues;
	
	
	
	
}
