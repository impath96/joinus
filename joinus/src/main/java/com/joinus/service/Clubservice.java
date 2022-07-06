package com.joinus.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.joinus.domain.ClubMembersVo;
import com.joinus.domain.ClubTotalBean;
import com.joinus.domain.ClubsVo;
import com.joinus.domain.ClubBoardsVo;

@Service
public interface Clubservice {

	//클럽 회원 리스트
	public List<ClubTotalBean> clubMemberListAll(int club_no);
	//클럽 정보
	public List<ClubsVo> clubInfo(int club_no);

	//클럽 리스트
	public List<ClubTotalBean> clubList(int interest_no);

	public List<ClubTotalBean> clubList();
	
	//모임장확인
	public String checkBoss(int club_no);
	//강퇴기능
	public void clubBan(int member_no);
	


	public void writeBoard(ClubBoardsVo vo);

	public void clubAuth(Integer member_no);




	
//	public List<ClubBoardVo> getBoardListAll(Integer club_no);
	
}
