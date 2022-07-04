package com.joinus.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class ClubsVo {
	
	private int club_no;
	private String club_name;
	private int club_capacity;
	private String club_content;
	private String club_image;
	private Date club_regdate;
	
	public ClubsVo() {}

	// alt shift s + o
	public ClubsVo(int club_no, String club_name, int club_capacity, String club_content, String club_image,
			Date club_regdate) {
		super();
		this.club_no = club_no;
		this.club_name = club_name;
		this.club_capacity = club_capacity;
		this.club_content = club_content;
		this.club_image = club_image;
		this.club_regdate = club_regdate;
	}
	


}
