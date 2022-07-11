package com.joinus.service;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.joinus.domain.ClubMembersVo;
import com.joinus.domain.ClubTotalBean;
import com.joinus.domain.ClubsVo;
import com.joinus.domain.Criteria;
import com.joinus.domain.MembersVo;
import com.joinus.persistence.ClubDao;
import org.springframework.stereotype.Service;

import com.joinus.domain.BoardCommentsVo;
import com.joinus.domain.BoardCriteria;
import com.joinus.domain.BoardLikesVo;
import com.joinus.domain.BoardTotalBean;
import com.joinus.domain.ClubBoardsVo;
import com.joinus.persistence.ClubDao;

import com.joinus.domain.ClubBoardsVo;

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
	public List<BoardTotalBean> getBoardListAll(Integer club_no, BoardCriteria cri) {
		return dao.getBoardListAll(club_no, cri);
	}
	
	@Override
	public Integer getTotalBoardCnt(int club_no) {
		return dao.getTotalBoardCnt(club_no);
	}

	@Override
	public List<BoardTotalBean> getBoardList(Integer club_no, Integer board_type_no, BoardCriteria cri) {
		return dao.getBoardList(club_no, board_type_no, cri);
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

	@Override
	public void writeComment(BoardCommentsVo vo) {
		dao.writeComment(vo);
	}

	@Override
	public int getCommentCnt(int club_board_no) {
		return dao.getCommentCnt(club_board_no);
	}

	@Override
	public List<BoardTotalBean> getCommentList(int club_board_no) {
		return dao.getCommentList(club_board_no);
	}

	@Override
	public void updateCommentCnt(int club_board_no) {
		dao.updateCommentCnt(club_board_no);
	}

	@Override
	public void updateComment(BoardCommentsVo vo) {
		dao.updateComment(vo);
	}

	@Override
	public void deleteComment(int board_comment_no) {
		dao.deleteComment(board_comment_no);
	}

	@Override
	public void decreaseCommentCnt(int club_board_no) {
		dao.decreaseCommentCnt(club_board_no);
	}

	@Override
	public int getLikeCnt(int club_board_no) {
		return dao.getLikeCnt(club_board_no);
	}

	@Override
	public int checkLike(int club_board_no, int member_no) {
		return dao.checkLike(club_board_no, member_no);
	}

	@Override
	public List<BoardTotalBean> getLikeList(int club_board_no) {
		return dao.getLikeList(club_board_no);
	}

	@Override
	public void insertLike(BoardLikesVo vo) {
		dao.insertLike(vo);
	}

	@Override
	public void increaseLikeCnt(int club_board_no) {
		dao.increaseLikeCnt(club_board_no);
	}

	@Override
	public void cancelLike(int club_board_no, int member_no) {
		dao.cancelLike(club_board_no, member_no);
	}

	@Override
	public void decreaseLikeCnt(int club_board_no) {
		dao.decreaseLikeCnt(club_board_no);
	}
	
	
	
	
	
}
