package com.joinus.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.joinus.domain.ClubBoardVo;
import com.joinus.persistence.ClubDao;

@Service
public class ClubServiceImpl implements ClubService {
	
	// Dao 객체 주입
	@Inject
	private ClubDao dao;

	@Override
	public void writeBoard(ClubBoardVo vo) {
		dao.writeBoard(vo);
		
	}

//	@Override
//	public List<ClubBoardVo> getBoardListAll(Integer club_no) {
//		return dao.getBoardListAll(club_no);
//	}
	
	
	
	
}
