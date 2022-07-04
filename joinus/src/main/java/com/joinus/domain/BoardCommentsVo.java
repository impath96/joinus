package com.joinus.domain;

import lombok.Data;

@Data
public class BoardCommentsVo {
	
	private int board_comment_no;
	private int club_board_no;
	private int club_no;
	private int member_no;
	private String board_comment_content;
	private String board_comment_date;
	private String board_comment_updatedate;
	
}
