package com.joinus.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.joinus.persistence.ClubDao;

@Service
public class ClubServiceImpl implements ClubService{
	
	private static final Logger log = LoggerFactory.getLogger(ClubServiceImpl.class);
	
	@Inject
	private ClubDao dao;
	
	//클럽 멤버 조회
	@Override
	public List<ClubTotalBean> clubMemberListAll(int club_no) {
		//log.info("clubMemberListAll() 호출");
		return dao.clubMemberList(club_no);
	}
	
	//클럽 수(관심사별) 조회
	@Override
	public Integer totalCnt(Integer interest_no) {
		//log.info("clubList-interest_no 호출");
		return dao.getTotalCnt(interest_no);
	}
	
	//전체 클럽 수 조회
	@Override
	public Integer totalCnt() {
		return dao.getTotalCnt();
	}

	//클럽 리스트(관심사별) 조회
	@Override
	public List<ClubTotalBean> clubList(int interest_no, Criteria cri) {
		
		log.info("clubList(interest_no) 호출");
		
		List<ClubTotalBean> result = dao.clubList(interest_no, cri);
		
		log.info(result+"");
		
		return result;
	}

	//전체 클럽 리스트 조회
	@Override
	public List<ClubTotalBean> clubList(Criteria cri) {
		
		log.info("clubList() 호출");
		return dao.clubList(cri);
	}
	
	//클럽 정보 조회
	@Override
	public List<ClubsVo> clubInfo(int club_no) {
		
		return dao.clubInfo(club_no);
	}
	
	//모임 회원 권한 조회
	@Override
	public Integer checkClubRole(int club_no, int member_no) {
		log.info("clubRole() 호출");
		return dao.clubRole(club_no, member_no);
	}
	
	//클럽 강퇴하기
	@Override
	public void clubBan(int member_no, int club_no) {
		
		dao.clubBan(member_no, club_no);
		
	}
	
	//클럽장 양도
	@Override
	public void clubAuth(Integer member_no, int club_no) {
		dao.clubAuth(member_no, club_no);
	}
	
	//클럽 나가기
	@Override
	public void clubLeave(MembersVo member, Integer club_no) {
		dao.clubLeave(member, club_no);
	}
	
	
	//===================================================================

	@Override
	public void writeBoard(ClubBoardsVo vo) {
		dao.writeBoard(vo);
		
	}

	@Override
	public List<BoardTotalBean> getBoardListAll(Integer club_no) {
		return dao.getBoardListAll(club_no);
	}

	@Override
	public List<BoardTotalBean> getBoardList(Integer club_no, Integer board_type_no) {
		return dao.getBoardList(club_no, board_type_no);
	}

	@Override
	public List<ClubBoardsVo> getBoardImageList(Integer club_no) {
		return dao.getBoardImageList(club_no);
	}

	@Override
	public BoardTotalBean getBoardContent(Integer club_board_no) {
		return dao.getBoardContent(club_board_no);
	}

	@Override
	public void modifyBoardContent(ClubBoardsVo vo) {
		dao.modifyBoardContent(vo);
	}

	@Override
	public void deleteBoard(Integer club_board_no) {
		dao.deleteBoard(club_board_no);
	}

	
	
	
	
	
	//=========================강성민========================
	
		//회원정보 가져오기
		@Override
		public MembersVo getMember(Integer num) {
			return dao.getMember(num);
		}
		//회원관심사 가져오기
		@Override
		public InterestsVo getMemberInterest(Integer num) {
			return dao.interest(num);
		}
		//회원이 선택한 관심사의 세부관심사리스트 가져오기
		@Override
		public List<InterestDetailsVo> getDetailName(Integer num) {
			return dao.getDetailName(num);
		}
	
		//회원이 입력한 클럽정보 저장
		@Override
		public void newClub(ClubsVo vo) {
			dao.newClub(vo);
		}
		//회원이 선택한 관심사 넘버값 가져오기
		@Override
		public InterestDetailsVo getInterestNo(String name) {
			return dao.getInterestNo(name);
		}
		//회원이 입력한 클럽관심사 저장하기
		@Override
		public void newClubInterest(Integer club_no, Integer interest_no, Integer interest_detail_no) {
			dao.newClubInterest(club_no, interest_no, interest_detail_no);
		}

		// 모임 이름 중복체크
		@Override
		public ClubsVo checkClubName(String name) {
			return dao.checkClubName(name);
		}
		
		//모임가입하기
		@Override
		public void join(ClubMembersVo members) {
			dao.join(members);
		}
		//모임별점주기
		@Override
		public void clubGrade(ClubGradesVo vo) {
			dao.clubGrade(vo);
		}
		//모임 별점 정보 가져오기
		@Override
		public List<ClubGradesVo> getClubGrade(Integer num) {
			return dao.getClubGrade(num);
		}
		//모임 별점 평균값, 참여자 수 가져오기
		@Override
		public List<Map<String, Integer>> getClubAvgCnt(Integer num) {
			return dao.getClubAvgCnt(num);
		}
		//모임정보가져오기
		@Override
		public ClubsVo getClubInfo(Integer num) {
			return dao.getClubInfo(num);
		}
		//모임 관심사 가져오기
		@Override
		public String getClubInterestDName(Integer num) {
			return dao.getClubInterestDName(num);
		}

		//모임회원정보가져오기
		@Override
		public List<ClubMembersVo> getClubMembers(Integer num) {
			return dao.getClubMembers(num);
		}

		// 모임 회원 정보 가져오기(특정)
		@Override
		public ClubMembersVo getClubMemberNo(Integer num, Integer num2) {
			return dao.getClubMemberNo(num,num2);
		}
		// 모임 별점 참여자 가져오기
		@Override
		public Integer getGradeinfo(Integer num, Integer num2) {
			return dao.getGradeinfo(num,num2);
		}

		// 모임 찜하기
		@Override
		public void clubDip(Integer num, Integer num2) {
			dao.clubDip(num, num2);
		}
		// 모임 찜 여부 확인
		@Override
		public Integer dip(Integer num) {
			return dao.dip(num);
		}

		// 모임 찜 취소
		@Override
		public void dipX(Integer num, Integer num2) {
			dao.dipX(num, num2);
		}

		//정모 리스트
		@Override
		public List<ClubMeetingsVo> getMeetings(Integer num) {
			// TODO Auto-generated method stub
			return null;
		}

		//게시글 리스트
		@Override
		public List<ClubBoardsVo> getBoards(Integer num) {
			// TODO Auto-generated method stub
			return null;
		}


		
		
	
}
