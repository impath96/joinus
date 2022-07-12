package com.joinus.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joinus.domain.BoardTotalBean;
import com.joinus.domain.ClubBoardsVo;
import com.joinus.domain.ClubGradesVo;
import com.joinus.domain.ClubMeetingsVo;
import com.joinus.domain.ClubMembersVo;
import com.joinus.domain.ClubTotalBean;
import com.joinus.domain.ClubsVo;
import com.joinus.domain.Criteria;
import com.joinus.domain.InterestDetailsVo;
import com.joinus.domain.InterestsVo;
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
		
		// 모임 이름 중복체크
		public ClubsVo checkClubName(String name);
		
		//모임가입하기
		public void join(ClubMembersVo members);
		//모임별점주기
		public void clubGrade(ClubGradesVo vo);
		//모임 별점 참여자
		public Integer getGradeinfo(Integer num, Integer num2);
		//모임 별점 정보 가져오기
		public List<ClubGradesVo> getClubGrade(Integer num);
		//모임 별점 평균값, 참여자 수 가져오기
		public List<Map<String, Integer>> getClubAvgCnt(Integer num);
		
		//모임 정보 가져오기
		public ClubsVo getClubInfo(Integer num);
		// 모임 관심사 가져오기
		public String getClubInterestDName(Integer num);
		//모임 회원 정보 가져오기
		public List<ClubMembersVo> getClubMembers(Integer num);	
		//모임 회원 정보 가져오기(특정)
		public ClubMembersVo getClubMemberNo(Integer num, Integer num2);
	
		// 모임 찜하기
		public void clubDip(Integer num,Integer num2);
		// 모임 찜 여부 확인
		public Integer dip(Integer num);
		// 모임 찜 취소
		public void dipX(Integer num,Integer num2);
		
		//정모 리스트
		public List<ClubMeetingsVo> getMeetings(Integer num);
		//게시글 리스트
		public List<ClubBoardsVo> getBoards(Integer num);

		
	
}
