package com.joinus.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.joinus.domain.ClubMemberVo;
import com.joinus.domain.ClubTotalBean;
import com.joinus.persistence.ClubDao;
import org.springframework.stereotype.Service;

import com.joinus.domain.BoardTotalBean;
import com.joinus.domain.ClubBoardVo;
import com.joinus.persistence.ClubDao;

@Service
public class ClubServiceImpl implements ClubService{
	
	private static final Logger log = LoggerFactory.getLogger(ClubServiceImpl.class);
	
	@Inject
	private ClubDao dao;
	

	@Override
	public List<ClubTotalBean> clubMemberListAll(int club_no) {
		
		log.info("clubMemberListAll() 호출");
		
		return dao.clubMemberList(club_no);
		
	}


	@Override
	public List<ClubTotalBean> clubList(int interest_no) {
		
		log.info("clubList() 호출");
		
		return dao.clubList(interest_no);
	}


	@Override
	public List<ClubTotalBean> clubList() {
		
		log.info("clubList() 호출");
		
		return dao.clubList();
	}



	

	@Override
	public void writeBoard(ClubBoardVo vo) {
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
	
	
	
	
}
