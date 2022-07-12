package com.joinus.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

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

@Repository
public class ClubDaoImpl implements ClubDao{
	
	@Inject
	private SqlSession sqlSession;
	
	static final String NAMESPACE ="com.joinus.mapper.ClubMapper";
	
	private static final Logger log = LoggerFactory.getLogger(ClubDaoImpl.class);
	
	//클럽 멤버 조회
	@Override
	public List<ClubTotalBean> clubMemberList(int club_no) {
		
		return sqlSession.selectList(NAMESPACE+".ClubMemberList", club_no);
	}
	
	//클럽 수(관심사별) 조회
	@Override
	public Integer getTotalCnt(Integer interest_no) {
		
		return sqlSession.selectOne(NAMESPACE+".CountClub", interest_no);
	}
	
	//전체 클럽 수 조회
	@Override
	public Integer getTotalCnt() {
		
		return sqlSession.selectOne(NAMESPACE+".CountClub");
	}
	
	//클럽 리스트(관심사별) 조회
	@Override
	public List<ClubTotalBean> clubList(int interest_no, Criteria cri) {
		
		Map<String, Object> param= new HashMap<String, Object>();
		param.put("interest_no", interest_no);
		param.put("cri", cri);
		
		//log.info(param+"");
		
		List<ClubTotalBean> result = sqlSession.selectList(NAMESPACE+".ClubList", param);
		
		//log.info(result+"");
		
		return result;
		
	}

	//전체 클럽 리스트 조회
	@Override
	public List<ClubTotalBean> clubList(Criteria cri) {
		
		//log.info(cri.getPageStart()+"");
		
		List<ClubTotalBean> result = sqlSession.selectList(NAMESPACE+".ClubListAll", cri);
		
		//log.info(result+"");
		
		return result;
	}
	
	//클럽 정보 조회
	@Override
	public List<ClubsVo> clubInfo(int club_no) {
		
		return sqlSession.selectList(NAMESPACE+".ClubInfo", club_no);
	}
	
	//회원 권한 조회
	@Override
	public Integer clubRole(int club_no, int member_no) {
		
		Map<String, Object> param= new HashMap<String, Object>();
		param.put("club_no", club_no);
		param.put("member_no", member_no);
		log.info("param : " + param);
		
		String role = sqlSession.selectOne(NAMESPACE+".ClubRole",param);
		log.info("role : "+role);

		Integer result = 0;
		if(role == null) {
			result = 3;
		}else if (role.equals("admin")){
			result = 2;
		}else if (role.equals("common")){
			result = 1;
		}
			
		return (Integer)result;
	}

	//클럽 강퇴하기
	@Override
	public void clubBan(int member_no, int club_no) {
		
		Map<String, Object> param= new HashMap<String, Object>();
		param.put("club_no", club_no);
		param.put("member_no", member_no);
		
		List<ClubMembersVo> vanMember = sqlSession.selectList(NAMESPACE+".ClubBan",param);
		
		log.info("vanMember : " + vanMember);
		sqlSession.insert(NAMESPACE+".VanMemberInsert",vanMember);
		log.info("회원정보 이동 완료");
		sqlSession.delete(NAMESPACE+".ClubMemberBan",member_no);
		log.info("강퇴완료");
		
	}
	
	//클럽장 양도
	@Override
	public void clubAuth(Integer member_no, int club_no) {
		
		Map<String, Object> param= new HashMap<String, Object>();
		param.put("club_no", club_no);
		param.put("member_no", member_no);
		
		sqlSession.update(NAMESPACE+".ClubMemberAuth1", param);
		log.info("모임장 권한 삭제");
		sqlSession.update(NAMESPACE+".ClubMemberAuth2", param);
		log.info("새 모임장 생성");
		
	}
	
	//클럽 나가기
	@Override
	public void clubLeave(MembersVo member, Integer club_no) {
		
		Map<String, Object> param= new HashMap<String, Object>();
		param.put("member", member);
		param.put("club_no", club_no);
		
		sqlSession.delete(NAMESPACE+".ClubLeave", param);
		log.info("모임나가기 완료");
		
		
		
	}
	
//=======================허수빈=============================================================
	

	@Override
	public void writeBoard(ClubBoardsVo vo) {
		log.info(" write() 호출 ");
		
		// 정보 전달받아서 mapper를 통해 db저장
		sqlSession.insert(NAMESPACE+".writeBoard", vo);
		
	}

	@Override
	public List<BoardTotalBean> getBoardListAll(Integer club_no) {
		log.info(" getBoardListAll() 호출 ");
		
		return sqlSession.selectList(NAMESPACE+".getBoardListAll", club_no);
	}
	
	
	
	
//=======================강성민=============================================================
	
		
		//회원정보 가져오기
		@Override
		public InterestsVo interest(Integer num) {
			return sqlSession.selectOne(NAMESPACE+".getMemberInterest",num);
		}
		//회원관심사 가져오기
		@Override
		public MembersVo getMember(Integer num) {
			return sqlSession.selectOne(NAMESPACE+".getMember", num);
		}
		
		//회원이 선택한 관심사의 세부관심사리스트 가져오기
		@Override
		public List<InterestDetailsVo> getDetailName(Integer num) {
			return sqlSession.selectList(NAMESPACE+".getInterestNameDetails", num);
		}

		
		//회원이 입력한 클럽정보 저장하기
		@Override
		public void newClub(ClubsVo vo) {
			sqlSession.insert(NAMESPACE+".createClub", vo);
		}
		//회원이 선택한 관심사 넘버값 가져오기
		@Override
		public InterestDetailsVo getInterestNo(String name) {
			return sqlSession.selectOne(NAMESPACE+".getInterestNo", name);
		}
		//회원이 입력한 클럽관심사 저장하기
		@Override
		public void newClubInterest(Integer club_no, Integer interest_no, Integer interest_detail_no) {
			Map<String, Integer> num = new HashMap<String, Integer>();
			num.put("club_no", club_no);
			num.put("interest_no", interest_no);
			num.put("interest_detail_no", interest_detail_no);
			
			sqlSession.insert(NAMESPACE+".createClubInterest", num);
		}
		
		// 모임 이름 중복체크
		@Override
		public ClubsVo checkClubName(String name) {
			return sqlSession.selectOne(NAMESPACE+".checkClubName", name);
		}
		
		//모임가입하기
		@Override
		public void join(ClubMembersVo members) {
			sqlSession.insert(NAMESPACE+".joinMembers",members);
		}

		//모임정보가져오기
		@Override
		public ClubsVo getClubInfo(Integer num) {
			return sqlSession.selectOne(NAMESPACE+".getClubInfo", num);
		}
		
		//모임 관심사 가져오기
		@Override
		public String getClubInterestDName(Integer num) {
			return sqlSession.selectOne(NAMESPACE+".getClubInterestDName", num);
		}

		//모임회원정보가져오기
		@Override
		public List<ClubMembersVo> getClubMembers(Integer num) {
			return sqlSession.selectList(NAMESPACE+".getClubMember", num);
		}
		// 모임 회원 정보 가져오기(특정)
		@Override
		public ClubMembersVo getClubMemberNo(Integer num, Integer num2) {
			Map<String, Integer> nummm = new HashMap<String, Integer>();
			nummm.put("club_no", num);
			nummm.put("member_no", num2);
			return sqlSession.selectOne(NAMESPACE+".getClubMemberNo", nummm);
		}
		
		//별점주기
		@Override
		public void clubGrade(ClubGradesVo vo) {
			sqlSession.selectList(NAMESPACE+".clubGrade", vo);		
		}
		//별점정보 가져오기
		@Override
		public List<ClubGradesVo> getClubGrade(Integer num) {
			return sqlSession.selectList(NAMESPACE+".getClubGrade", num);
		}
		//별점 평균값,참여자수 가져오기
		@Override
		public List<Map<String, Integer>> getClubAvgCnt(Integer num) {
			List<Map<String, Integer>> list = sqlSession.selectList(NAMESPACE+".getGradeOption", num);
			return list;
		}

		// 모임 별점 참여자 가져오기
		@Override
		public Integer getGradeinfo(Integer num, Integer num2) {
			Map<String, Integer> numm = new HashMap<String, Integer>();
			numm.put("club_no", num);
			numm.put("member_no", num2);
			return sqlSession.selectOne(NAMESPACE+".clubBtn", numm);
		}

		// 모임 찜하기
		@Override
		public void clubDip(Integer num, Integer num2) {
			Map<String, Integer> dipNums = new HashMap<String, Integer>();
			dipNums.put("member_no", num);
			dipNums.put("club_no", num2);
			sqlSession.insert(NAMESPACE+".clubDip", dipNums);
		}
		// 모임 찜 여부 확인
		@Override
		public Integer dip(Integer num) {
			return sqlSession.selectOne(NAMESPACE+".dipCheck", num);
		}
		
		// 모임 찜 취소
		@Override
		public void dipX(Integer num, Integer num2) {
			Map<String, Integer> dipx = new HashMap<String, Integer>();
			dipx.put("member_no", num);
			dipx.put("club_no", num2);
			sqlSession.insert(NAMESPACE+".dipX", dipx);
		}


		//정모 리스트
		@Override
		public List<ClubMeetingsVo> getMeetings(Integer num) {
			return sqlSession.selectList(NAMESPACE+".getMeetingList", num);
		}

		//게시글 리스트
		@Override
		public List<ClubBoardsVo> getBoards(Integer num) {
			return sqlSession.selectList(NAMESPACE+".getBoardList", num);
		}
		
		

		
		
		
	
	
	

	@Override
	public List<BoardTotalBean> getBoardList(Integer club_no, Integer board_type_no) {
		log.info(" getBoardList() 호출 ");
		
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("club_no", club_no);
		param.put("board_type_no", board_type_no);
		
		
		return sqlSession.selectList(NAMESPACE+".getBoardList", param);
	}

	@Override
	public List<ClubBoardsVo> getBoardImageList(Integer club_no) {
		return sqlSession.selectList(NAMESPACE+".getBoardImageList", club_no);
	}

	@Override
	public BoardTotalBean getBoardContent(Integer club_board_no) {
		return sqlSession.selectOne(NAMESPACE+".getBoardContent", club_board_no);
	}

	@Override
	public void modifyBoardContent(ClubBoardsVo vo) {
		sqlSession.update(NAMESPACE+".modifyBoardContent", vo);
	}

	@Override
	public void deleteBoard(Integer club_board_no) {
		sqlSession.delete(NAMESPACE+".deleteBoard", club_board_no);
	}

}
