package com.joinus.domain;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class ClubBoardsVo {
	
	private int club_board_no;
	private int club_no;
	private int member_no;
	private int board_type_no;
	private String club_board_title;
	private String club_board_content;
	private String club_board_photo;
	private Date club_board_date;
	private int club_board_like;
	//private String club_board_comment;
	
	public ClubBoardsVo() {}
	// alt shift s + o
	public ClubBoardsVo(int club_board_no, int club_no, int member_no, int board_type_no, String club_board_title,
			String club_board_content, String club_board_photo, Date club_board_date, int club_board_like) {
		super();
		this.club_board_no = club_board_no;
		this.club_no = club_no;
		this.member_no = member_no;
		this.board_type_no = board_type_no;
		this.club_board_title = club_board_title;
		this.club_board_content = club_board_content;
		this.club_board_photo = club_board_photo;
		this.club_board_date = club_board_date;
		this.club_board_like = club_board_like;
	}

	
	
	
	
}
