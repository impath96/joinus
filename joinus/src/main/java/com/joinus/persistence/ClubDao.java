package com.joinus.persistence;


import java.util.List;

import com.joinus.domain.ClubMembersVo;
import com.joinus.domain.ClubTotalBean;
import com.joinus.domain.ClubsVo;
import com.joinus.domain.Criteria;
import com.joinus.domain.MeetingTotalBean;
import com.joinus.domain.MembersVo;
import com.joinus.domain.RentalPlacesVo;
import com.joinus.domain.ClubBoardsVo;
import com.joinus.domain.ClubMeetingsVo;

public interface ClubDao {
	
	//=========================고은비=========================
	//클럽회원 리스트
	public List<ClubTotalBean> clubMemberList(int club_no);
	
	//클럽 전체 개수 조회
	public Integer getTotalCnt(Integer interest_no);
	public Integer getTotalCnt();
	
	//클럽리스트(관심사o)
	public List<ClubTotalBean> clubList(int interest_no, Criteria cri);

	//클럽리스트(관심사x)
	public List<ClubTotalBean> clubList(Criteria cri);
	
	//클럽 정보
	public List<ClubsVo> clubInfo(int club_no);
	
	//모임 회원 권한 가져오기
	public Integer clubRole(int club_no, int member_no);
	
	//클럽 강퇴
	public void clubBan(int member_no, int club_no);
	
	//모임장 양도
	public void clubAuth(Integer member_no, int club_no);
	
	//클럽 나가기
	public void clubLeave(MembersVo member, Integer club_no);
	
	//예약정보 가져오기 - LIST
	public List<MeetingTotalBean> getRental(int member_no);
	
	//예약정보 가져오기 - REST
	public List<MeetingTotalBean> getRentalREST(int rental_places_no);
	//정모 만들기
	public void createMeeting(ClubMeetingsVo vo);
	
	//=========================고은비=========================
	
	
	//=========================허수빈========================
	// 글쓰기
	public void writeBoard(ClubBoardsVo vo);
	
	// 모임고유값에 따른 게시글리스트
	public List<ClubBoardsVo> getBoardListAll(Integer club_no);
	//=========================허수빈========================






	

	
}
