package com.joinus.persistence;

import com.joinus.domain.ClubBoardVo;

public interface ClubDao {
	
	// 글쓰기
	public void boardWrite(ClubBoardVo vo);
	
}
