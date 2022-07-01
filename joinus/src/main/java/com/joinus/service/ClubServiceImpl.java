package com.joinus.service;

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
	public void boardWrite(ClubBoardVo vo) {
		dao.boardWrite(vo);
		
	}

	
	
}
