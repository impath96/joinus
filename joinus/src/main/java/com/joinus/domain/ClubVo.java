package com.joinus.domain;

import lombok.Data;

@Data
public class ClubVo {
	
	private int club_no;
	private String club_name;
	private String club_content;
	private String club_image;
	
	public ClubVo() {}
	// alt shift s + o
	public ClubVo(int club_no, String club_name, String club_content, String club_image) {
		super();
		this.club_no = club_no;
		this.club_name = club_name;
		this.club_content = club_content;
		this.club_image = club_image;
	}


}
