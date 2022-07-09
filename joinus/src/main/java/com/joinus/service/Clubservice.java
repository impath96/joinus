package com.joinus.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.joinus.domain.BoardTotalBean;
import com.joinus.domain.ClubBoardsVo;
import com.joinus.domain.ClubTotalBean;
import com.joinus.domain.ClubsVo;
import com.joinus.domain.Criteria;
import com.joinus.domain.MembersVo;

@Service
public interface Clubservice {

	//클럽 회원 리스트
	public List<ClubTotalBean> clubMemberListAll(int club_no);
	//클럽 정보
	public List<ClubsVo> clubInfo(int club_no);

	//클럽 리스트
	public List<ClubTotalBean> clubList(int interest_no, Criteria cri);

	public List<ClubTotalBean> clubList(Criteria cri);
	
	//클럽 개수 조회
	public Integer totalCnt(Integer interest_no);
	
	public Integer totalCnt();
	
	//모임 회원 권한 조회
	public Integer checkClubRole(int club_no, int member_no);
	
	//강퇴기능
	public void clubBan(int member_no, int club_no);
	



	public void clubAuth(Integer member_no, int club_no);

	public void clubLeave(MembersVo member, Integer club_no);



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
