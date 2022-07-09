package com.joinus.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.joinus.domain.ClubBoardsVo;
import com.joinus.domain.ClubGradesVo;
import com.joinus.domain.ClubMembersVo;
import com.joinus.domain.ClubTotalBean;
import com.joinus.domain.ClubsVo;
import com.joinus.domain.InterestDetailsVo;
import com.joinus.domain.InterestsVo;
import com.joinus.domain.MembersVo;

@Repository
public class ClubDaoImpl implements ClubDao{
	
	@Inject
	private SqlSession sqlSession;
	
	static final String NAMESPACE ="com.joinus.mapper.ClubMapper";
	
	private static final Logger log = LoggerFactory.getLogger(ClubDaoImpl.class);

	
	
	
	@Override
	public List<ClubTotalBean> clubMemberList(int club_no) {
		
		log.info("clubMemberList() - 호출");
		
		List<ClubTotalBean> result = sqlSession.selectList(NAMESPACE+".ClubMemberList",club_no);
		
		//log.info(result+"");
		
		return result;
	}
	
	
	@Override
	public List<ClubTotalBean> clubList(int interest_no) {
		
		List<ClubTotalBean> result = sqlSession.selectList(NAMESPACE+".ClubList",interest_no);
		
		//log.info(result+"");
		
		return result;
		
	}


	@Override
	public List<ClubTotalBean> clubList() {
		
		List<ClubTotalBean> result = sqlSession.selectList(NAMESPACE+".ClubListAll");
		
		//log.info(result+"");
		
		return result;
	}

//=======================허수빈=============================================================
	

	@Override
	public void writeBoard(ClubBoardsVo vo) {
		log.info(" write() 호출 ");
		
		// 정보 전달받아서 mapper를 통해 db저장
		sqlSession.insert(NAMESPACE+".writeBoard", vo);
		
	}

	@Override
	public List<ClubBoardsVo> getBoardListAll(Integer club_no) {
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
		//모임회원정보가져오기
		@Override
		public List<ClubMembersVo> getClubMembers(Integer num) {
			return sqlSession.selectList(NAMESPACE+".getClubMember", num);
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


		


		
		
		
	
	
	
}
