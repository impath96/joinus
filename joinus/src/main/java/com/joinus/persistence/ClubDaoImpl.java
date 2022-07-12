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
import com.joinus.domain.ClubMeetingsVo;
import com.joinus.domain.ClubMembersVo;
import com.joinus.domain.ClubTotalBean;
import com.joinus.domain.ClubsVo;
import com.joinus.domain.Criteria;
import com.joinus.domain.MeetingTotalBean;
import com.joinus.domain.MembersVo;
import com.joinus.domain.RentalPlacesVo;

@Repository
public class ClubDaoImpl implements ClubDao{
	
	@Inject
	private SqlSession sqlSession;
	
	static final String NAMESPACE ="com.joinus.mapper.ClubMapper";
	static final String NAMESPACE2 ="com.joinus.mapper.MeetingMapper";
	
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
	
	//예약정보 가져오기
	@Override
	public List<MeetingTotalBean> getRental(int member_no) {
		
		return sqlSession.selectList(NAMESPACE2+".GetRental", member_no);
	}
	
	//예약정보 가져오기 - REST
	@Override
	public List<MeetingTotalBean> getRentalREST(int rental_places_no) {
		return sqlSession.selectList(NAMESPACE2+".GetRentalREST", rental_places_no);
	}
	
	//정모 생성
	@Override
	public void createMeeting(ClubMeetingsVo vo) {
		sqlSession.insert(NAMESPACE2+".CreateMeeting", vo);
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




















	
	
	
	
	
	
}
