package com.joinus.service;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.joinus.domain.ClubTotalBean;
import com.joinus.domain.ClubsVo;
import com.joinus.persistence.ClubDao;

import com.joinus.domain.ClubBoardsVo;

@Service
public class ClubServiceImpl implements Clubservice{
	
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
	public List<ClubsVo> clubInfo(int club_no) {
		
		
		return dao.clubInfo(club_no);
	}
	
	@Override
	public String checkBoss(int club_no) {
		log.info("clubBoss() 호출");
		return dao.clubBoss(club_no);
	}

	@Override
	public void clubBan(int member_no) {
		
		dao.clubBan(member_no);
		
	}
	
	
	@Override
	public void clubAuth(Integer member_no) {
		dao.clubAuth(member_no);
	}
	
	//===================================================================

	@Override
	public void writeBoard(ClubBoardsVo vo) {
		dao.writeBoard(vo);
		
	}













//	@Override
//	public List<ClubBoardVo> getBoardListAll(Integer club_no) {
//		return dao.getBoardListAll(club_no);
//	}
	
	
	
	
}
