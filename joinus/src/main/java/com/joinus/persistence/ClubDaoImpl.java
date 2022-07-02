package com.joinus.persistence;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.joinus.domain.ClubBoardVo;

@Repository
public class ClubDaoImpl implements ClubDao {
	
	private static final Logger log = LoggerFactory.getLogger(ClubDaoImpl.class);
	
	// 디비연결 및 mapper 연결처리 객체
	@Inject
	private SqlSession sqlSession;
	
	static final String NAMESPACE = "com.joinus.mapper.ClubMapper";

	@Override
	public void writeBoard(ClubBoardVo vo) {
		log.info(" write() 호출 ");
		
		// 정보 전달받아서 mapper를 통해 db저장
		sqlSession.insert(NAMESPACE+".writeBoard", vo);
		
	}

	@Override
	public List<ClubBoardVo> getBoardListAll(Integer club_no) {
		log.info(" getBoardListAll() 호출 ");
		
		return sqlSession.selectList(NAMESPACE+".getBoardListAll", club_no);
	}
	
	
	
	
	
	
}
