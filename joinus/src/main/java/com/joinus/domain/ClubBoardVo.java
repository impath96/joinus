package com.joinus.domain;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClubBoardVo {
	
	private int club_board_no;
	private int club_no;
	private int board_type_no;
	// 작성자
	private String club_board_title;
	private String club_board_content;
	private String club_board_photo;
	private Date club_board_date;
	private int club_board_like;
	private String club_board_comment;
	
}
