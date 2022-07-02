package com.joinus.persistence;

import java.util.List;

import com.joinus.domain.ClubBoardVo;

public interface ClubDao {
	
	// 글쓰기
	public void writeBoard(ClubBoardVo vo);
	
	// 모임고유값에 따른 게시글리스트
	public List<ClubBoardVo> getBoardListAll(Integer club_no);
	
}
