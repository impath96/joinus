package com.joinus.persistence;


import java.util.List;

import com.joinus.domain.ClubMembersVo;
import com.joinus.domain.ClubTotalBean;
import com.joinus.domain.ClubsVo;
import com.joinus.domain.ClubBoardsVo;

public interface ClubDao {
	
	//=========================고은비=========================
	//클럽회원 리스트
	public List<ClubTotalBean> clubMemberList(int club_no);
	
	//클럽리스트(관심사o)
	public List<ClubTotalBean> clubList(int interest_no);

	//클럽리스트(관심사x)
	public List<ClubTotalBean> clubList();
	
	//클럽 정보
	public List<ClubsVo> clubInfo(int club_no);
	
	//모임장 id 가져오기
	public String clubBoss(int club_no);
	
	//클럽 강퇴
	public void clubBan(int member_no);
	
	//모임장 양도
	public void clubAuth(Integer member_no);
	
	//=========================고은비=========================
	
	
	//=========================허수빈========================
	// 글쓰기
	public void writeBoard(ClubBoardsVo vo);
	
	// 모임고유값에 따른 게시글리스트
	public List<ClubBoardsVo> getBoardListAll(Integer club_no);
	//=========================허수빈========================



	

	
}
