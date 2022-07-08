package com.joinus.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joinus.domain.ClubBoardsVo;
import com.joinus.domain.ClubGradesVo;
import com.joinus.domain.ClubMembersVo;
import com.joinus.domain.ClubTotalBean;
import com.joinus.domain.ClubsVo;
import com.joinus.domain.InterestDetailsVo;
import com.joinus.domain.InterestsVo;
import com.joinus.domain.MembersVo;

@Service
public interface ClubService {

	//클럽 회원 리스트
	public List<ClubTotalBean> clubMemberListAll(int club_no);

	//클럽 리스트
	public List<ClubTotalBean> clubList(int interest_no);

	public List<ClubTotalBean> clubList();
	
	


	
	public void writeBoard(ClubBoardsVo vo);
	
//	public List<ClubBoardVo> getBoardListAll(Integer club_no);
	
	
	
	
	
	//=========================강성민========================
	
		//회원정보 가져오기
		public MembersVo getMember(Integer num);
		//회원관심사 가져오기
		public InterestsVo getMemberInterest(Integer num);
		//회원이 선택한 관심사의 세부관심사리스트 가져오기
		public List<InterestDetailsVo> getDetailName(Integer num);
		
		//회원이 입력한 클럽정보 저장
		public void newClub(ClubsVo vo);
		//회원이 선택한 관심사 넘버값 가져오기
		public InterestDetailsVo getInterestNo(String name);
		//회원이 입력한 클럽관심사 저장하기
		public void newClubInterest(Integer club_no, Integer interest_no,Integer interest_detail_no);
			
		//모임가입하기
		public void join(ClubMembersVo members);
		//모임별점주기
		public void clubGrade(ClubGradesVo vo);
		
		//모임 별점 정보 가져오기
		public List<ClubGradesVo> getClubGrade(Integer num);
		//모임 별점 평균값, 참여자 수 가져오기
		public List<Map<String, Integer>> getClubAvgCnt(Integer num);
		
		//모임 정보 가져오기
		public ClubsVo getClubInfo(Integer num);
		//모임 회원 정보 가져오기
		public List<ClubMembersVo> getClubMembers(Integer num);	
	
	
}
