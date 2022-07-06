package com.joinus.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.joinus.domain.ClubBoardsVo;
import com.joinus.domain.ClubTotalBean;

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
	
	
	
	
	
	
}
