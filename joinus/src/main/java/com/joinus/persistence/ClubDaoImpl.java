package com.joinus.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.joinus.domain.BoardCriteria;
import com.joinus.domain.BoardTotalBean;
import com.joinus.domain.ClubBoardsVo;
import com.joinus.domain.ClubTotalBean;

@Repository
public class ClubDaoImpl implements ClubDao{
	
	@Inject
	private SqlSession sqlSession;
	
	static final String NAMESPACE ="com.joinus.mapper.ClubMapper";
	
	private static final Logger log = LoggerFactory.getLogger(ClubDaoImpl.class);

//	@Override
//	public List<ClubTotalBean> clubMemberList(int club_no) {
//		
//		log.info("clubMemberList() - 호출");
//		
//		List<ClubTotalBean> result = sqlSession.selectList(NAMESPACE+".ClubMemberList",club_no);
//		
//		//log.info(result+"");
//		
//		return result;
//	}
//	
//	
//	@Override
//	public List<ClubTotalBean> clubList(int interest_no) {
//		
//		List<ClubTotalBean> result = sqlSession.selectList(NAMESPACE+".ClubList",interest_no);
//		
//		//log.info(result+"");
//		
//		return result;
//		
//	}
//
//
//	@Override
//	public List<ClubTotalBean> clubList() {
//		
//		List<ClubTotalBean> result = sqlSession.selectList(NAMESPACE+".ClubListAll");
//		
//		//log.info(result+"");
//		
//		return result;
//	}

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
	public ClubBoardsVo getBoardContent(Integer club_board_no) {
		return sqlSession.selectOne(NAMESPACE+".getBoardContent", club_board_no);
	}

	
	
	
	
	
	
	
	
}
