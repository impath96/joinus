package com.joinus.persistence;


import java.util.List;

import com.joinus.domain.ClubMembersVo;
import com.joinus.domain.ClubTotalBean;
import com.joinus.domain.BoardCriteria;
import com.joinus.domain.BoardTotalBean;
import com.joinus.domain.ClubBoardsVo;

public interface ClubDao {
	
	//=========================고은비=========================
	//클럽회원 리스트
//	public List<ClubTotalBean> clubMemberList(int club_no);
	
	//클럽리스트
//	public List<ClubTotalBean> clubList(int interest_no);

//	public List<ClubTotalBean> clubList();
	//=========================고은비=========================
	
	
	//=========================허수빈========================
	// 글쓰기
	public void writeBoard(ClubBoardsVo vo);
	
	// 모임고유값에 따른 게시글리스트
	public List<BoardTotalBean> getBoardListAll(Integer club_no);
	
	public List<BoardTotalBean> getBoardList(Integer club_no, Integer board_type_no);
	
	// 게시글 페이징처리(전체)
	public List<BoardTotalBean> getBoardListAll(Integer club_no, Integer page, Integer size);
	
	// 게시글 페이징처리(카테고리별)
	
	//=========================허수빈========================
}
