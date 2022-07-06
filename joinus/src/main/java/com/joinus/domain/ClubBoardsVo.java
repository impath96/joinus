package com.joinus.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class ClubBoardsVo {

	private int lub_board_no; 
	private int lub_no; 
	private int oard_type_no;
	private int ember_no; 
	private String lub_board_title; 
	private String lub_board_content; 
	private String lub_board_image; 
	private Date lub_board_date; 
	private Date lub_board_updatedate;
	private int lub_board_like;
	private int lub_board_commentcnt;
	
}
