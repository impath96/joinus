package com.joinus.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.joinus.domain.BoardTotalBean;
import com.joinus.domain.ClubBoardsVo;

@Service
public interface ClubService {

//	//클럽 회원 리스트
//	public List<ClubTotalBean> clubMemberListAll(int club_no);
//
//	//클럽 리스트
//	public List<ClubTotalBean> clubList(int interest_no);
//
//	public List<ClubTotalBean> clubList();
	
	


	
	public void writeBoard(ClubBoardsVo vo);
	
	public List<BoardTotalBean> getBoardListAll(Integer club_no);
	
	public List<BoardTotalBean> getBoardList(Integer club_no, Integer board_type_no);
	
	public List<ClubBoardsVo> getBoardImageList(Integer club_no);
	
	// 게시글 본문
	//public ClubBoardsVo getBoardContent(Integer club_board_no);
	public BoardTotalBean getBoardContent(Integer club_board_no);
	
	// 게시글 수정
	public void modifyBoardContent(ClubBoardsVo vo);
	
	// 게시글 삭제
	public void deleteBoard(Integer club_board_no);
	
	
}
