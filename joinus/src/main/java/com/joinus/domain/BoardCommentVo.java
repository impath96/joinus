package com.joinus.domain;

import lombok.Data;

@Data
public class BoardCommentVo {
	
	private int board_comment_no;
	private int club_board_no;
	private int club_no;
	private int member_no;
	private String board_comment_content;
}
