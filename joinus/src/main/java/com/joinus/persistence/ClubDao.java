package com.joinus.persistence;


import java.util.List;

import com.joinus.domain.ClubMemberVo;
import com.joinus.domain.ClubTotalBean;
import com.joinus.domain.ClubBoardVo;

public interface ClubDao {
	
	//=========================고은비=========================
	//클럽회원 리스트
	public List<ClubTotalBean> clubMemberList(int club_no);
	
	//클럽리스트
	public List<ClubTotalBean> clubList(int interest_no);

	public List<ClubTotalBean> clubList();
	//=========================고은비=========================
	
	
	//=========================허수빈========================
	// 글쓰기
	public void writeBoard(ClubBoardVo vo);
	
	// 모임고유값에 따른 게시글리스트
	public List<ClubBoardVo> getBoardListAll(Integer club_no);
	//=========================허수빈========================
}
