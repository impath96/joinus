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
	
	public List<ClubBoardsVo> getBoardImageList(Integer club_no);
	
	// 게시글 본문
//	public ClubBoardsVo getBoardContent(Integer club_board_no);
	public BoardTotalBean getBoardContent(Integer club_board_no);
	
	// 게시글 수정
	public void modifyBoardContent(ClubBoardsVo vo);
	
	// 게시글 삭제
	public void deleteBoard(Integer club_board_no);
	
	
	//=========================허수빈========================
}
