package com.joinus.domain;

import lombok.Data;

@Data
public class BoardTotalBean {
	
	private MembersVo membersVo;
	private ClubBoardsVo clubBoardsVo;
	private ClubsVo clubsVo;
	private BoardTypesVo boardTypesVo;
	private BoardCommentsVo boardCommentsVo;
	private BoardLikesVo boardLikesVo;

}
