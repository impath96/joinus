package com.joinus.domain;

import java.sql.Date;

public class ClubBoardVo {
	
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
	
	
	public int getClub_board_no() {
		return club_board_no;
	}
	public void setClub_board_no(int club_board_no) {
		this.club_board_no = club_board_no;
	}
	public int getClub_no() {
		return club_no;
	}
	public void setClub_no(int club_no) {
		this.club_no = club_no;
	}
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	public int getBoard_type_no() {
		return board_type_no;
	}
	public void setBoard_type_no(int board_type_no) {
		this.board_type_no = board_type_no;
	}
	public String getClub_board_title() {
		return club_board_title;
	}
	public void setClub_board_title(String club_board_title) {
		this.club_board_title = club_board_title;
	}
	public String getClub_board_content() {
		return club_board_content;
	}
	public void setClub_board_content(String club_board_content) {
		this.club_board_content = club_board_content;
	}
	public String getClub_board_photo() {
		return club_board_photo;
	}
	public void setClub_board_photo(String club_board_photo) {
		this.club_board_photo = club_board_photo;
	}
	public Date getClub_board_date() {
		return club_board_date;
	}
	public void setClub_board_date(Date club_board_date) {
		this.club_board_date = club_board_date;
	}
	public int getClub_board_like() {
		return club_board_like;
	}
	public void setClub_board_like(int club_board_like) {
		this.club_board_like = club_board_like;
	}
	
	
	@Override
	public String toString() {
		return "ClubBoardVo [club_board_no=" + club_board_no + ", club_no=" + club_no + ", member_no=" + member_no
				+ ", board_type_no=" + board_type_no + ", club_board_title=" + club_board_title
				+ ", club_board_content=" + club_board_content + ", club_board_photo=" + club_board_photo
				+ ", club_board_date=" + club_board_date + ", club_board_like=" + club_board_like + "]";
	}
	
	
	
	
}
