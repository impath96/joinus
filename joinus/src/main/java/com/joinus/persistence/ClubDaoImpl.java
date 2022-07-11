package com.joinus.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.joinus.domain.BoardCommentsVo;
import com.joinus.domain.BoardCriteria;
import com.joinus.domain.BoardLikesVo;
import com.joinus.domain.BoardTotalBean;
import com.joinus.domain.ClubBoardsVo;
import com.joinus.domain.ClubMembersVo;
import com.joinus.domain.ClubTotalBean;
import com.joinus.domain.ClubsVo;
import com.joinus.domain.Criteria;
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
//		log.info("@@@@@@"+club_no+", "+cri);
//		Map<String, Object> param = new HashMap<String, Object>();
//		param.put("club_no", club_no);
//		param.put("pageStart", cri.getPageStart());
//		param.put("perPageNum", cri.getPerPageNum());
		
		return sqlSession.selectList(NAMESPACE+".getBoardListAll", club_no);
	}
	
	@Override
	public Integer getTotalBoardCnt() {
		return sqlSession.selectOne(NAMESPACE+".totalBoardCnt");
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

	@Override
	public void writeComment(BoardCommentsVo vo) {
		sqlSession.insert(NAMESPACE+".writeComment", vo);
	}

	@Override
	public int getCommentCnt(int club_board_no) {
		return sqlSession.selectOne(NAMESPACE+".commentCnt", club_board_no);
	}

	@Override
	public List<BoardTotalBean> getCommentList(int club_board_no) {
		return sqlSession.selectList(NAMESPACE+".getCommentList", club_board_no);
	}

	@Override
	public void updateCommentCnt(int club_board_no) {
		sqlSession.update(NAMESPACE+".updateCommentCnt", club_board_no);
	}

	@Override
	public void updateComment(BoardCommentsVo vo) {
		sqlSession.update(NAMESPACE+".updateComment", vo);
	}

	@Override
	public void deleteComment(int board_comment_no) {
		sqlSession.delete(NAMESPACE+".deleteComment", board_comment_no);
	}

	@Override
	public void decreaseCommentCnt(int club_board_no) {
		sqlSession.update(NAMESPACE+".decreaseCommentCnt", club_board_no);
	}

	@Override
	public int getLikeCnt(int club_board_no) {
		return sqlSession.selectOne(NAMESPACE+".likeCnt", club_board_no);
	}

	@Override
	public int checkLike(int club_board_no, int member_no) {
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("club_board_no", club_board_no);
		param.put("member_no", member_no);
		
		return sqlSession.selectOne(NAMESPACE+".checkLike", param);
	}

	@Override
	public List<BoardTotalBean> getLikeList(int club_board_no) {
		return sqlSession.selectList(NAMESPACE+".boardLikeList", club_board_no);
	}

	@Override
	public void insertLike(BoardLikesVo vo) {
		sqlSession.insert(NAMESPACE+".insertLike", vo);
	}

	@Override
	public void increaseLikeCnt(int club_board_no) {
		sqlSession.update(NAMESPACE+".increaseLikeCnt", club_board_no);
	}

	@Override
	public void cancelLike(int club_board_no, int member_no) {
		Map<String, Integer> param = new HashMap<String, Integer>();
		param.put("club_board_no", club_board_no);
		param.put("member_no", member_no);
		
		sqlSession.delete(NAMESPACE+".cancelLike", param);
	}

	@Override
	public void decreaseLikeCnt(int club_board_no) {
		sqlSession.update(NAMESPACE+".decreaseLikeCnt", club_board_no);
	}
	
	
	
	

}
