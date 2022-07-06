package com.joinus.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.joinus.domain.ClubBoardsVo;
import com.joinus.domain.ClubMembersVo;
import com.joinus.domain.ClubTotalBean;
import com.joinus.domain.ClubsVo;

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
	public String clubBoss(int club_no) {
		
		String Boss_email = sqlSession.selectOne(NAMESPACE+".ClubBoss",club_no);
		
		log.info(Boss_email);
		
		return Boss_email;
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
		
		log.info(result+"");
		
		return result;
	}
	
	@Override
	public List<ClubsVo> clubInfo(int club_no) {
		
		return sqlSession.selectList(NAMESPACE+".ClubInfo", club_no);
	}


	@Override
	public void clubBan(int member_no) {
		List<ClubMembersVo> vanMember = sqlSession.selectList(NAMESPACE+".ClubBan",member_no);
		
		log.info("vanMember : " + vanMember);
		sqlSession.insert(NAMESPACE+".VanMemberInsert",vanMember);
		log.info("회원정보 이동 완료");
		sqlSession.delete(NAMESPACE+".ClubMemberBan",member_no);
		log.info("강퇴완료");
		
	}
	
	@Override
	public void clubAuth(Integer member_no) {
		sqlSession.update(NAMESPACE+".ClubMemberAuth1", member_no);
		log.info("모임장 권한 삭제");
		sqlSession.update(NAMESPACE+".ClubMemberAuth2", member_no);
		log.info("새 모임장 생성");
		
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
